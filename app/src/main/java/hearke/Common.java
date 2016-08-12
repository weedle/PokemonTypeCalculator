package hearke;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class Common {
    public enum Effect {
        STRONG, WEAK, NULL, NEUTRAL,
        SUPERSTRONG, SUPERWEAK
    }
    public enum TypeId {
        NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE,
        FIGHTING, POISON, GROUND, FLYING, PSYCHIC, BUG,
        ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY
    }

    public static String toString(TypeId type) {
        switch(type) {
            case NORMAL:
                return "normal";
            case FIRE:
                return "fire";
            case WATER:
                return "water";
            case ELECTRIC:
                return "electric";
            case GRASS:
                return "grass";
            case ICE:
                return "ice";
            case FIGHTING:
                return "fighting";
            case POISON:
                return "poison";
            case GROUND:
                return "ground";
            case FLYING:
                return "flying";
            case PSYCHIC:
                return "psychic";
            case BUG:
                return "bug";
            case ROCK:
                return "rock";
            case GHOST:
                return "ghost";
            case DRAGON:
                return "dragon";
            case DARK:
                return "dark";
            case STEEL:
                return "steel";
            case FAIRY:
                return "fairy";
            default:
                return "failure";
        }
    }

    public static TypeId toTypeId(String str) {
        switch(str.toLowerCase()) {
            case "normal":
                return TypeId.NORMAL;
            case "fire":
                return TypeId.FIRE;
            case "water":
                return TypeId.WATER;
            case "electric":
                return TypeId.ELECTRIC;
            case "grass":
                return TypeId.GRASS;
            case "ice":
                return TypeId.ICE;
            case "fighting":
                return TypeId.FIGHTING;
            case "poison":
                return TypeId.POISON;
            case "ground":
                return TypeId.GROUND;
            case "flying":
                return TypeId.FLYING;
            case "psychic":
                return TypeId.PSYCHIC;
            case "bug":
                return TypeId.BUG;
            case "rock":
                return TypeId.ROCK;
            case "ghost":
                return TypeId.GHOST;
            case "dragon":
                return TypeId.DRAGON;
            case "dark":
                return TypeId.DARK;
            case "steel":
                return TypeId.STEEL;
            case "fairy":
                return TypeId.FAIRY;
            default:
                return TypeId.NORMAL;
        }
    }

    public static TypeId fromString(String input) {
        TypeId type = TypeId.NORMAL;
        for( TypeId typeInner : TypeId.values() ) {
            if( input.toLowerCase().equals(Common.toString(typeInner)) ) type = typeInner;
        }
        return type;
    }

    public static Map<TypeId, Row> getDataMap() {
        Map<TypeId, Row> dataMap = Collections.synchronizedMap(new EnumMap<TypeId, Row>(TypeId.class));
        LinkedList<Row> rows = new LinkedList<Row>();

        Row rowNormal = new Row(TypeId.NORMAL);
        rowNormal.setClassOfValues(new TypeId[]{TypeId.ROCK,  TypeId.STEEL}, Effect.WEAK);
        rowNormal.setClassOfValues(new TypeId[]{TypeId.GHOST}, Effect.NULL);
        rows.add(rowNormal);

        Row rowFire = new Row(TypeId.FIRE);
        rowFire.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.WATER,
                TypeId.ROCK, TypeId.DRAGON}, Effect.WEAK);
        rowFire.setClassOfValues(new TypeId[]{TypeId.GRASS, TypeId.ICE,
                TypeId.BUG, TypeId.STEEL}, Effect.STRONG);
        rows.add(rowFire);

        Row rowWater = new Row(TypeId.WATER);
        rowWater.setClassOfValues(new TypeId[]{TypeId.WATER, TypeId.GRASS,
                TypeId.DRAGON}, Effect.WEAK);
        rowWater.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.GROUND,
                TypeId.ROCK}, Effect.STRONG);
        rows.add(rowWater);

        Row rowElectric = new Row(TypeId.ELECTRIC);
        rowElectric.setClassOfValues(new TypeId[]{TypeId.ELECTRIC, TypeId.GRASS,
                TypeId.DRAGON}, Effect.WEAK);
        rowElectric.setClassOfValues(new TypeId[]{TypeId.WATER, TypeId.FLYING}, Effect.STRONG);
        rowElectric.setClassOfValues(new TypeId[]{TypeId.GROUND}, Effect.NULL);
        rows.add(rowElectric);

        Row rowGrass = new Row(TypeId.GRASS);
        rowGrass.setClassOfValues(new TypeId[]{TypeId.FIRE,  TypeId.GRASS,
                TypeId.POISON, TypeId.FLYING, TypeId.BUG, TypeId.DRAGON,
                TypeId.STEEL}, Effect.WEAK);
        rowGrass.setClassOfValues(new TypeId[]{TypeId.WATER, TypeId.GROUND,
                TypeId.ROCK}, Effect.STRONG);
        rows.add(rowGrass);

        Row rowIce = new Row(TypeId.ICE);
        rowIce.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.WATER,
                TypeId.ICE, TypeId.STEEL}, Effect.WEAK);
        rowIce.setClassOfValues(new TypeId[]{TypeId.GRASS, TypeId.GROUND,
                TypeId.FLYING, TypeId.DRAGON}, Effect.STRONG);
        rows.add(rowIce);

        Row rowFighting = new Row(TypeId.FIGHTING);
        rowFighting.setClassOfValues(new TypeId[]{TypeId.POISON, TypeId.FLYING,
                TypeId.PSYCHIC, TypeId.BUG, TypeId.FAIRY}, Effect.WEAK);
        rowFighting.setClassOfValues(new TypeId[]{TypeId.NORMAL, TypeId.ICE,
                TypeId.ROCK, TypeId.DARK, TypeId.STEEL}, Effect.STRONG);
        rows.add(rowFighting);

        Row rowPoison = new Row(TypeId.POISON);
        rowPoison.setClassOfValues(new TypeId[]{TypeId.POISON, TypeId.GROUND,
                TypeId.ROCK, TypeId.GHOST}, Effect.WEAK);
        rowPoison.setClassOfValues(new TypeId[]{TypeId.GRASS, TypeId.FAIRY},
                Effect.STRONG);
        rowPoison.setClassOfValues(new TypeId[]{TypeId.STEEL}, Effect.NULL);
        rows.add(rowPoison);

        Row rowGround = new Row(TypeId.GROUND);
        rowGround.setClassOfValues(new TypeId[]{TypeId.GRASS, TypeId.BUG},
                Effect.WEAK);
        rowGround.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.ELECTRIC,
                TypeId.POISON, TypeId.ROCK, TypeId.STEEL}, Effect.STRONG);
        rowGround.setClassOfValues(new TypeId[]{TypeId.FLYING}, Effect.NULL);
        rows.add(rowGround);

        Row rowFlying = new Row(TypeId.FLYING);
        rowFlying.setClassOfValues(new TypeId[]{TypeId.ELECTRIC, TypeId.ROCK,
                TypeId.STEEL}, Effect.WEAK);
        rowFlying.setClassOfValues(new TypeId[]{TypeId.GRASS, TypeId.FIGHTING,
                TypeId.BUG}, Effect.STRONG);
        rows.add(rowFlying);

        Row rowPsychic = new Row(TypeId.PSYCHIC);
        rowPsychic.setClassOfValues(new TypeId[]{TypeId.PSYCHIC, TypeId.STEEL},
                Effect.WEAK);
        rowPsychic.setClassOfValues(new TypeId[]{TypeId.FIGHTING, TypeId.POISON},
                Effect.STRONG);
        rowPsychic.setClassOfValues(new TypeId[]{TypeId.DARK}, Effect.NULL);
        rows.add(rowPsychic);

        Row rowBug = new Row(TypeId.BUG);
        rowBug.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.FIGHTING,
                TypeId.POISON, TypeId.FLYING, TypeId.GHOST, TypeId.STEEL,
                TypeId.FAIRY}, Effect.WEAK);
        rowBug.setClassOfValues(new TypeId[]{TypeId.GRASS, TypeId.PSYCHIC,
                TypeId.DARK}, Effect.STRONG);
        rows.add(rowBug);

        Row rowRock = new Row(TypeId.ROCK);
        rowRock.setClassOfValues(new TypeId[]{TypeId.FIGHTING, TypeId.GROUND,
                TypeId.STEEL}, Effect.WEAK);
        rowRock.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.ICE,
                TypeId.FLYING, TypeId.BUG}, Effect.STRONG);
        rows.add(rowRock);

        Row rowGhost = new Row(TypeId.GHOST);
        rowGhost.setClassOfValues(new TypeId[]{TypeId.DARK}, Effect.WEAK);
        rowGhost.setClassOfValues(new TypeId[]{TypeId.PSYCHIC, TypeId.GHOST},
                Effect.STRONG);
        rowGhost.setClassOfValues(new TypeId[]{TypeId.NORMAL}, Effect.NULL);
        rows.add(rowGhost);

        Row rowDragon = new Row(TypeId.DRAGON);
        rowDragon.setClassOfValues(new TypeId[]{TypeId.STEEL}, Effect.WEAK);
        rowDragon.setClassOfValues(new TypeId[]{TypeId.DRAGON}, Effect.STRONG);
        rowDragon.setClassOfValues(new TypeId[]{TypeId.FAIRY}, Effect.NULL);
        rows.add(rowDragon);

        Row rowDark = new Row(TypeId.DARK);
        rowDark.setClassOfValues(new TypeId[]{TypeId.FIGHTING, TypeId.DARK,
                TypeId.FAIRY}, Effect.WEAK);
        rowDark.setClassOfValues(new TypeId[]{TypeId.PSYCHIC, TypeId.GHOST},
                Effect.STRONG);
        rows.add(rowDark);

        Row rowSteel = new Row(TypeId.STEEL);
        rowSteel.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.WATER,
                TypeId.ELECTRIC, TypeId.STEEL}, Effect.WEAK);
        rowSteel.setClassOfValues(new TypeId[]{TypeId.ICE, TypeId.ROCK,
                TypeId.FAIRY}, Effect.STRONG);
        rows.add(rowSteel);

        Row rowFairy = new Row(TypeId.FAIRY);
        rowFairy.setClassOfValues(new TypeId[]{TypeId.FIRE, TypeId.POISON,
                TypeId.STEEL}, Effect.WEAK);
        rowFairy.setClassOfValues(new TypeId[]{TypeId.FIGHTING, TypeId.DRAGON,
                TypeId.DARK}, Effect.STRONG);
        rows.add(rowFairy);


        for( Row row : rows ) {
            dataMap.put(row.getTypeId(), row);
        }

        return dataMap;
    }
}
