package hearke;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

import hearke.Common;
import hearke.Common.Effect;
import hearke.Common.TypeId;

public class TypeCalc {

    private static Map<TypeId, Row> dataMapAttack;
    private static Map<TypeId, Row> dataMapDefence;

    public static void instantiateMap() {
        dataMapDefence = Collections.synchronizedMap(new EnumMap<TypeId, Row>(TypeId.class));
        dataMapAttack = Common.getDataMap();


        // go through row elements
        for( TypeId type : TypeId.values() ) {
            Row row = new Row(type);
            //add to column vector
            for( TypeId typeInner : TypeId.values() ) {
                row.put(typeInner, dataMapAttack.get(typeInner).get(type));
            }
            dataMapDefence.put(type, row);
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        instantiateMap();

        System.out.print("1 or 2 types? (3 for attack info): ");
        String input = "";
        try {
            input = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if( input.equals("1"))
            type1();
        else if( input.equals("2"))
            type2();
        else if( input.equals("3"))
            type3();

        return;
    }

    public static void type1(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TypeId type = TypeId.NORMAL;

        System.out.print("Enter a type: ");
        String typeInput = "";
        try {
            typeInput = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        type = Common.fromString(typeInput);

        System.out.println("Type: " + type);
        System.out.print("Strong: ");
        for( TypeId typeInner : getStrengths(type) ) {
            System.out.print(typeInner + "   ");
        }
        System.out.print("\nWeak: ");
        for( TypeId typeInner : getWeaknesses(type) ) {
            System.out.print(typeInner + "   ");
        }
        System.out.print("\nNull: ");
        for( TypeId typeInner : getNulls(type) ) {
            System.out.print(typeInner + "   ");
        }

        return;
    }

    public static void type2(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TypeId type1 = TypeId.NORMAL;
        TypeId type2 = TypeId.NORMAL;

        System.out.print("Enter a type: ");
        String typeInput = "";
        try {
            typeInput = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        type1 = Common.fromString(typeInput);

        System.out.print("Enter another type: ");
        typeInput = "";
        try {
            typeInput = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        type2 = Common.fromString(typeInput);

        System.out.println("Types: " + type1 + ", " + type2);
        System.out.print("Strong: ");
        for( TypeId typeInner : getStrengths(type1, type2) ) {
            System.out.print(typeInner + "   ");
        }
        for( TypeId typeInner : getSuperStrengths(type1, type2) ) {
            System.out.print(typeInner + "4x   ");
        }
        System.out.print("\nWeak: ");
        for( TypeId typeInner : getWeaknesses(type1, type2) ) {
            System.out.print(typeInner + "   ");
        }
        for( TypeId typeInner : getSuperWeaknesses(type1, type2) ) {
            System.out.print(typeInner + "0.25x   ");
        }
        System.out.print("\nNull: ");
        for( TypeId typeInner : getNulls(type1, type2) ) {
            System.out.print(typeInner + "   ");
        }

        return;
    }

    public static void type3(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TypeId type = TypeId.NORMAL;

        System.out.print("Enter a type: ");
        String typeInput = "";
        try {
            typeInput = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        type = Common.fromString(typeInput);

        System.out.println("Type: " + type);
        System.out.print("Strong: ");
        for( TypeId typeInner : dataMapAttack.get(type).getClassOfValues(Effect.STRONG) ) {
            System.out.print(typeInner + "   ");
        }
        System.out.print("\nWeak: ");
        for( TypeId typeInner : dataMapAttack.get(type).getClassOfValues(Effect.WEAK) ) {
            System.out.print(typeInner + "   ");
        }
        System.out.print("\nNull: ");
        for( TypeId typeInner : dataMapAttack.get(type).getClassOfValues(Effect.NULL) ) {
            System.out.print(typeInner + "   ");
        }

        return;
    }
    public static LinkedList<TypeId> getStrengthsAtk(TypeId type) {
        return dataMapAttack.get(type).getClassOfValues(Effect.STRONG);
    }
    public static LinkedList<TypeId> getWeaknessesAtk(TypeId type) {
        return dataMapAttack.get(type).getClassOfValues(Effect.WEAK);
    }
    public static LinkedList<TypeId> getNullsAtk(TypeId type) {
        return dataMapAttack.get(type).getClassOfValues(Effect.NULL);
    }
    public static LinkedList<TypeId> getStrengths(TypeId type) {
        return dataMapDefence.get(type).getClassOfValues(Effect.STRONG);
    }

    public static LinkedList<TypeId> getSuperStrengths(TypeId type1, TypeId type2) {
        return getThings( type1, type2, Effect.SUPERSTRONG );
    }

    public static LinkedList<TypeId> getStrengths(TypeId type1, TypeId type2) {
        return getThings( type1, type2, Effect.STRONG );
    }

    public static LinkedList<TypeId> getSuperWeaknesses(TypeId type1, TypeId type2) {
        return getThings( type1, type2, Effect.SUPERWEAK );
    }

    public static LinkedList<TypeId> getWeaknesses(TypeId type1, TypeId type2) {
        return getThings( type1, type2, Effect.WEAK );
    }

    public static LinkedList<TypeId> getNulls(TypeId type1, TypeId type2) {
        return getThings( type1, type2, Effect.NULL );
    }

    public static LinkedList<TypeId> getThings(TypeId type1, TypeId type2, Effect effect) {
        LinkedList<TypeId> returnData = new LinkedList<>();

        LinkedList<TypeId> list1a = dataMapDefence.get(type1).getClassOfValues(Effect.STRONG);
        LinkedList<TypeId> list1b = dataMapDefence.get(type1).getClassOfValues(Effect.WEAK);
        LinkedList<TypeId> list1c  = dataMapDefence.get(type1).getClassOfValues(Effect.NULL);
        LinkedList<TypeId> list2a = dataMapDefence.get(type2).getClassOfValues(Effect.STRONG);
        LinkedList<TypeId> list2b = dataMapDefence.get(type2).getClassOfValues(Effect.WEAK);
        LinkedList<TypeId> list2c  = dataMapDefence.get(type2).getClassOfValues(Effect.NULL);

        if( effect.equals(Effect.STRONG) ) {
            for( TypeId type : list1a ) {
                if( !list2a.contains(type) &&!list2b.contains(type) && !list2c.contains(type) ) {
                    returnData.add(type);
                }
            }
        } else if( effect.equals(Effect.WEAK) ) {
            for( TypeId type : list1b ) {
                if( !list2a.contains(type) && !list2b.contains(type)&& !list2c.contains(type) ) {
                    returnData.add(type);
                }
            }
        } else if( effect.equals(Effect.NULL) ) {
            for( TypeId type : list1c ) {
                if( !list2c.contains(type) ) {
                    returnData.add(type);
                }
            }
        } else if( effect.equals(Effect.SUPERSTRONG) ) {
            for( TypeId type : list1a ) {
                if( list2a.contains(type) ) {
                    returnData.add(type);
                }
            }
        } else if( effect.equals(Effect.SUPERWEAK) ) {
            for( TypeId type : list1b ) {
                if( list2b.contains(type) ) {
                    returnData.add(type);
                }
            }
        }

        return returnData;
    }

    public static LinkedList<TypeId> getWeaknesses(TypeId type) {
        return dataMapDefence.get(type).getClassOfValues(Effect.WEAK);
    }

    public static LinkedList<TypeId> getNulls(TypeId type) {
        return dataMapDefence.get(type).getClassOfValues(Effect.NULL);
    }
}
