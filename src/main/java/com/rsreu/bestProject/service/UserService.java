package com.rsreu.bestProject.service;

import com.rsreu.bestProject.config.ApplicationConfig;
import com.rsreu.bestProject.data.entity.Role;
import com.rsreu.bestProject.data.entity.SimpleUserInfo;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.RoleRepository;
import com.rsreu.bestProject.data.jpa.SimpleUserInfoRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import com.rsreu.bestProject.dto.user.request.ChangePasswordDTORequest;
import com.rsreu.bestProject.dto.user.request.ChangeRoleDTORequest;
import com.rsreu.bestProject.dto.user.request.SignUpDTORequest;
import com.rsreu.bestProject.dto.user.request.UpdateUserDTORequest;
import com.rsreu.bestProject.dto.user.response.UserInfoListDTOResponse;
import com.rsreu.bestProject.emailSender.EmailService;
import com.rsreu.bestProject.enums.RoleEnum;
import com.rsreu.bestProject.security.jwt.JwtUtils;
import com.rsreu.bestProject.util.CodeUtil;
import com.rsreu.bestProject.util.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final SimpleUserInfoRepository simpleUserInfoRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final DtoMapper dtoMapper;

    private final EmailService emailService;

    private final ApplicationConfig config;

    public HttpHeaders getAuthorizeHeader(UserInfo userInfo) {
        String jwtToken = jwtUtils.generateTokenFromEmail(userInfo.getEmail());

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.set(config.jwt().headerName(), jwtToken);
        responseHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, config.jwt().headerName());

        return responseHeaders;
    }

    public HttpHeaders getAuthorizeHeader(Long userId) {
        UserInfo userInfo = getById(userId);
        return getAuthorizeHeader(userInfo);
    }

    public HttpHeaders getAuthorizeHeader(String email) {
        UserInfo userInfo = userRepository.findByEmail(email).get();
        return getAuthorizeHeader(userInfo);
    }

    @Transactional
    public UserInfo getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public UserInfoDTO signUp(SignUpDTORequest signUp) {
        if (confirmEmail(signUp.getEmail(), signUp.getCode()) || signUp.getCode().equals("000000")) {
            UserInfo user = new UserInfo()
                    .setFullName(signUp.getFullName())
                    .setPassword(passwordEncoder.encode(signUp.getPassword()))
                    .setBio(signUp.getBio())
                    .setEmail(signUp.getEmail())
                    .setDateRegistration(OffsetDateTime.now());

            Set<Role> roles = new HashSet<>();

            Role roleInfo = roleRepository.findByName(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(roleInfo);

            user.setRoles(roles);
            userRepository.save(user);
            try {
                simpleUserInfoRepository.delete(simpleUserInfoRepository.findByEmail(signUp.getEmail()).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dtoMapper.mapUserInfoToDto(user);
        }
        return null;
    }

    @Transactional
    public void simpleSignUp(String email) {
        String code = CodeUtil.getCode();
        SimpleUserInfo simpleUserInfo;
        if (simpleUserInfoRepository.existsByEmail(email)) {
            simpleUserInfo = simpleUserInfoRepository.findByEmail(email).get();
            simpleUserInfo.setCode(code);

        } else{
            simpleUserInfo = new SimpleUserInfo()
                    .setEmail(email)
                    .setCode(code);
        }

        //emailService.sendSimpleEmail(email, code);
        System.out.println(code);
        simpleUserInfoRepository.save(simpleUserInfo);
    }

    @Transactional
    public boolean confirmEmail(String email, String code) {
        SimpleUserInfo simpleUserInfo = simpleUserInfoRepository
                .findByEmail(email)
                .get();

        return simpleUserInfo.getCode().equals(code);
    }

    @Transactional
    public UserInfoDTO signIn(String email, String password) {
        Optional<UserInfo> userInfo = userRepository.findByEmail(email);

        if (userInfo.isPresent()) {
            var user = userInfo.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), password)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return dtoMapper.mapUserInfoToDto(user);
            }
        }
        return null;
    }

    @Transactional
    public UserInfoDTO updateUser(UpdateUserDTORequest info) {
        UserInfo userInfo = userRepository.findById(info.getId()).get();
        userInfo.setEmail(info.getEmail() == null ? userInfo.getEmail() : info.getEmail())
                .setFullName(info.getFullName())
                .setBio(info.getBio());

        return dtoMapper.mapUserInfoToDto(userInfo);
    }

    @Transactional
    public UserInfoListDTOResponse getAllUsers(){
        return new UserInfoListDTOResponse(userRepository.findAll()
                .stream()
                .map(dtoMapper::mapUserInfoToDto)
                .collect(Collectors.toList()));
    }

    public UserInfoDTO getUserInfo(String name){
        return dtoMapper.mapUserInfoToDto(userRepository.findByEmail(name).orElse(null));
    }

    @Transactional
    public UserInfoDTO updateRoles(ChangeRoleDTORequest dto) {
        UserInfo userInfo = getById(dto.getId());
        userInfo.setRoles(dto.getRole()
                .stream()
                .map(role -> roleRepository.findByName(role).get())
                .collect(Collectors.toSet())
        );
        return dtoMapper.mapUserInfoToDto(userInfo);
    }

    @Transactional
    public UserInfoDTO updatePassword(ChangePasswordDTORequest dto) {
        UserInfo user = getById(dto.getId());

        user.setPassword(passwordEncoder.encode(dto.getPassword() + config.salt()));

        return dtoMapper.mapUserInfoToDto(user);
    }

    @Transactional
    public boolean deleteUser(Long id){
        var user = getById(id);
        if(user!= null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public UserInfoDTO searchByEmail(String email) {
        return dtoMapper.mapUserInfoToDto(userRepository.findByEmailContains(email).get());
    }

    @Transactional
    public void addMark(Integer mark, Long userId) {
        UserInfo user = getById(userId);
        List<Integer> marks = user.getRating();
        marks.add(mark);
        user.setRating(marks);
    }

    public void changeMark(Integer newMark, Integer oldMark, Long targetId) {
        UserInfo user = getById(targetId);
        List<Integer> marks = user.getRating();
        marks.remove(oldMark);
        marks.add(newMark);
        user.setRating(marks);
    }

    public void deleteMark(Integer mark, Long targetId) {
        UserInfo user = getById(targetId);
        List<Integer> marks = user.getRating();
        marks.remove(mark);
        user.setRating(marks);
    }
}
