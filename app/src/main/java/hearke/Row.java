package hearke;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

import hearke.Common.Effect;
import hearke.Common.TypeId;

// Row contains all the data for a single row of the pokemon type table
// that's the table containing all the info on which type beat what and stuff
public class Row {

    // this particular row
    private TypeId typeId;
    // values for this row
    // by default, set all interactions to neutral
    private Map<TypeId, Effect> data;

    // constructor
    // sets default values for this row
    public Row(TypeId id) {
        typeId = id;
        data = new EnumMap<TypeId, Effect>(TypeId.class);
        for( TypeId type : TypeId.values() ) {
            data.put(type, Effect.NEUTRAL);
        }
    }

    // modify a single entry of the row
    public void put(TypeId id, Effect effect) {
        data.put(id, effect);
    }

    // get a single entry of the row
    public Effect get(TypeId id) {
        return data.get(id);
    }

    // get this guy's id
    public TypeId getTypeId() {
        return typeId;
    }
    // get all the elements with a certain type
    // eg, all the elements this guy is strong against
    // or all the ones it is null or neutral against
    public LinkedList<TypeId> getClassOfValues(Effect effect) {
        LinkedList<TypeId> list = new LinkedList<>();
        for( TypeId type : TypeId.values() ) {
            if( data.get(type).equals(effect) ) {
                list.add(type);
            }
        }
        return list;
    }

    // set all given typeIds to a specific effect
    // good to initialize a row pretty quickly
    // eg, for fire, give grass, ice, bug, and steel and set
    // effect to strong for all
    public void setClassOfValues(TypeId[] types, Effect effect) {
        for ( TypeId type : types ) {
            data.put(type, effect);
        }
    }
}
