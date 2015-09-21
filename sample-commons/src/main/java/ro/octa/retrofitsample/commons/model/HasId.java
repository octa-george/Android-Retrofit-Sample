package ro.octa.retrofitsample.commons.model;

import java.io.Serializable;

/**
 * @author Octa on 9/21/2015.
 */
public interface HasId<ID extends Serializable> {

    ID getId();
}
