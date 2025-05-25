package com.lms.authms.domain.mapper;

import com.lms.authms.domain.dto.RegisterRequest;
import com.lms.authms.domain.entity.User;
import com.lms.authms.domain.mapper.helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapperHelper.class)
public interface UserMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    User toUser(RegisterRequest registerRequest);
}
