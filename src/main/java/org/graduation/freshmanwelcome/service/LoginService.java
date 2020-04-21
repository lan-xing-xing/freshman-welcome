package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.ResultDTO;

public interface LoginService {
    ResultDTO login(String username, String password);

    Object getUserInfo(String token);
}
