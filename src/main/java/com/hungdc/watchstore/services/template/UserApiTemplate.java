package com.hungdc.watchstore.services.template;

import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.entities.User;

public abstract class UserApiTemplate {
    public User execute(String id, UserDto dto){
        User userResponse = new User();
        validationUserDTO(dto);
        try{
            if(hasId(id)){
                User user = getUserFromDatabase(id);
                if(hasDTO(dto)){
                    mapUserDtoToUser(dto,user);
                    performRequestSpecificAction(user);
                    saveUser(user);
                    responseUser(userResponse,dto);
                }
                else{
                    saveUser(user);
                    responseUser(userResponse,dto);
                }

            }
            else{
                User user = createUser();
                mapUserDtoToUser(dto,user);
                performRequestSpecificAction(user);
                saveUser(user);
                responseUser(userResponse,dto);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return userResponse;
    }

    private boolean hasDTO(UserDto dto) {
        return dto != null;
    }

    protected abstract User createUser();

    protected abstract void responseUser(User user, UserDto dto);

    protected abstract void saveUser(User user);

    protected abstract void performRequestSpecificAction(User user);

    protected abstract void mapUserDtoToUser(UserDto dto, User user);

    protected abstract User getUserFromDatabase(String id);

    protected abstract void validationUserDTO(UserDto dto);

    protected boolean hasId(String id) {
        return id != null && !id.isEmpty();
    }
}
