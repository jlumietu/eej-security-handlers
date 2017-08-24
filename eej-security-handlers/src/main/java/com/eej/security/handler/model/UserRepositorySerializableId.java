package com.eej.security.handler.model;

import java.io.Serializable;

import com.erax.principal.PrincipalSerializableId;

/**
 * Any UserDetails, User or Principal implementation often have a field which acts as 
 * a key field from a User Repository.
 * 
 * For example, a User Database allways have one or more fields declared as primary key
 * or unique which could be used to map this data base entity to another tables or entities.
 * 
 * This interface pretends to be a accomplishing contract to all this kind of UserDetails, 
 * User or Principal implementations which are loaded in Security Context during authentication
 * stage, to make possible then to relate this with any other action.  
 * 
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public interface UserRepositorySerializableId extends Serializable, PrincipalSerializableId{

}