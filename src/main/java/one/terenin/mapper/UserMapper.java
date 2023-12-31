package one.terenin.mapper;

import one.terenin.dto.request.UserRegisterRequest;
import one.terenin.dto.request.UserRequest;
import one.terenin.dto.response.UserResponse;
import one.terenin.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserEntity map(UserRequest request);
    UserResponse map(UserEntity entity);
    UserResponse map(UserRegisterRequest request);
}
