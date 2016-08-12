package com.sentinel.hearke.pokemoninfo;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import hearke.Common;
import hearke.TypeCalc;
import hearke.Row;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private LinkedList<Integer> chkboxIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkboxIds = new LinkedList<>();
        populateCheckboxes();
        TypeCalc.instantiateMap();
    }

    // Called when someone presses the Retrieve Info button
    public void retrieveInfo(View view) {
        //TypeCalc.instantiateMap();
        System.out.println("num: " + getNumChecked());

        Common.TypeId type1 = Common.toTypeId( getCheckedOne().toString() );
        Common.TypeId type2 = Common.toTypeId( getCheckedTwo().toString() );
        System.out.println("type1: " + getCheckedOne().toString() );
        System.out.println("type1: " + type1.toString() );
        System.out.println("type2: " + getCheckedTwo().toString() );
        System.out.println("type2: " + type2.toString() );

        LinkedList<Common.TypeId> list;
        String info = "";

        if( getMode() == 1 ) {
            list = TypeCalc.getStrengths(type1);
            info = "Effective Attack Types: ";
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "  ";
            }
            info = info  +"\n\n";

            list = TypeCalc.getWeaknesses(type1);
            info = info + "Ineffective Attack Types: ";
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "  ";
            }
            info = info  +"\n\n";

            list = TypeCalc.getNulls(type1);
            info = info + "Null: ";
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "  ";
            }
            info = info  +"\n\n";
        } else if( getMode() == 2 ) {
            list = TypeCalc.getStrengths(type1,type2);
            info = "Effective Attack Types: ";
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "  ";
            }
            list = TypeCalc.getSuperStrengths(type1,type2);
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "[4x]  ";
            }
            info = info  +"\n\n";

            list = TypeCalc.getWeaknesses(type1,type2);
            info = info + "Ineffective Attack Types: ";
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "  ";
            }
            list = TypeCalc.getSuperWeaknesses(type1,type2);
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "[0.25x]  ";
            }
            info = info  +"\n\n";

            list = TypeCalc.getNulls(type1,type2);
            info = info + "Null: ";
            for( Common.TypeId type : list ) {
                info = info + type.toString() + "  ";
            }
            info = info  +"\n\n";
        } else if( getMode() == 3 ) {
            list = TypeCalc.getStrengthsAtk(type1);
            info = "Effective Against: ";
            for (Common.TypeId type : list) {
                info = info + type.toString() + "  ";
            }
            info = info + "\n\n";

            list = TypeCalc.getWeaknessesAtk(type1);
            info = info + "Ineffective Against: ";
            for (Common.TypeId type : list) {
                info = info + type.toString() + "  ";
            }
            info = info + "\n\n";

            list = TypeCalc.getNullsAtk(type1);
            info = info + "Null Against: ";
            for (Common.TypeId type : list) {
                info = info + type.toString() + "  ";
            }
            info = info + "\n\n";
        }
        TextView tv = (TextView)findViewById(R.id.txtInfo);
        tv.setText(info);

        decheckAll();

    }

    // Called when any checkbox is selected
    public void chkClicked(View view) {
        CheckBox chk = (CheckBox) view;
        System.out.println("id: " + chk.getText());
        if( chk.isChecked() ) {
            if (getMode() == 1 || getMode() == 3) {
                decheckAll();
                chk.setChecked(true);
            } else if (getMode() == 2) {
                if( getNumChecked() > 2 ) {
                    chk.setChecked(false);
                }
            }
        }
    }

    private void populateCheckboxes() {
        chkboxIds.add(R.id.chkNormal);
        chkboxIds.add(R.id.chkFire);
        chkboxIds.add(R.id.chkWater);
        chkboxIds.add(R.id.chkElectric);
        chkboxIds.add(R.id.chkGrass);
        chkboxIds.add(R.id.chkIce);

        chkboxIds.add(R.id.chkFighting);
        chkboxIds.add(R.id.chkPoison);
        chkboxIds.add(R.id.chkGround);
        chkboxIds.add(R.id.chkFlying);
        chkboxIds.add(R.id.chkPsychic);
        chkboxIds.add(R.id.chkBug);

        chkboxIds.add(R.id.chkRock);
        chkboxIds.add(R.id.chkGhost);
        chkboxIds.add(R.id.chkDragon);
        chkboxIds.add(R.id.chkDark);
        chkboxIds.add(R.id.chkSteel);
        chkboxIds.add(R.id.chkFairy);
    }

    private int getNumChecked() {
        int count = 0;
        for( int id : chkboxIds) {
            CheckBox chkbox = (CheckBox) findViewById(id);
            if( chkbox.isChecked() ) count++;
        }
        return count;
    }

    private void decheckAll() {
        for( int id : chkboxIds) {
            CheckBox chkbox = (CheckBox) findViewById(id);
            chkbox.setChecked(false);
        }
    }

    private int getMode() {
        RadioButton rad1 = (RadioButton) findViewById(R.id.radType1);
        RadioButton rad2 = (RadioButton) findViewById(R.id.radType2);
        RadioButton rad3 = (RadioButton) findViewById(R.id.radAttack);

        if (rad1.isChecked()) {
            return 1;
        } else if (rad2.isChecked()) {
            return 2;
        } else {
            return 3;
        }
    }

    private Common.TypeId getCheckedOne() {
        for( int id : chkboxIds) {
            CheckBox chkbox = (CheckBox) findViewById(id);
            if( chkbox.isChecked() ) {
                return Common.toTypeId( chkbox.getText().toString() );
            }
        }
        return Common.TypeId.NORMAL;
    }
    private Common.TypeId getCheckedTwo() {
        Common.TypeId type1 = getCheckedOne();
        for( int id : chkboxIds) {
            CheckBox chkbox = (CheckBox) findViewById(id);
            if( chkbox.isChecked() && !type1.equals(
                        Common.toTypeId( chkbox.getText().toString() ) ) )
            return Common.toTypeId( chkbox.getText().toString() );
        }
        return Common.TypeId.NORMAL;
    }
}
