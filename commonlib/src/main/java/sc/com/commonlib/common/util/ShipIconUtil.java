package sc.com.commonlib.common.util;

import android.text.TextUtils;

import sc.com.commonlib.R;
import sc.com.commonlib.common.constant.ShipConstant;

/**
 * Created by Eric on 2018/4/3.
 */

public class ShipIconUtil {


    public static int getIconDrawableId(String type) {
        if (TextUtils.isEmpty(type)) {

            return R.drawable.utility_items_icon;

        } else if (ShipConstant.TYPE_Radar.equals(type)) {

            return R.drawable.radar_icon;

        } else if (ShipConstant.TYPE_Computers.equals(type)) {
            return R.drawable.computers_icon;

        } else if (ShipConstant.TYPE_PowerPlants.equals(type)) {
            return R.drawable.power_plants_icon;

        } else if (ShipConstant.TYPE_Coolers.equals(type)) {
            return R.drawable.coolers_icon;

        } else if (ShipConstant.TYPE_ShieldGenerators.equals(type)) {
            return R.drawable.shield_generators_icon;

        } else if (ShipConstant.TYPE_FuelIntakes.equals(type)) {
            return R.drawable.fuel_intakes_icon;

        } else if (ShipConstant.TYPE_FuelTanks.equals(type)) {
            return R.drawable.fuel_tanks_icon;

        } else if (ShipConstant.TYPE_QuantumDrives.equals(type)) {
            return R.drawable.quantum_drives_icon;

        } else if (ShipConstant.TYPE_JumpModules.equals(type)) {
            return R.drawable.jump_modules_icon;

        } else if (ShipConstant.TYPE_QuantumFuelTanks.equals(type)) {
            return R.drawable.quantum_fuel_tanks_icon;

        } else if (ShipConstant.TYPE_MainThrusters.equals(type)) {
            return R.drawable.main_thrusters_icon;

        } else if (ShipConstant.TYPE_ManeuveringThrusters.equals(type)) {
            return R.drawable.maneuvering_thrusters_icon;

        } else if (ShipConstant.TYPE_Weapons.equals(type)) {
            return R.drawable.weapons_icon;

        } else if (ShipConstant.TYPE_Missiles.equals(type)) {
            return R.drawable.missiles_icon;

        } else if (ShipConstant.TYPE_Turrets.equals(type)) {
            return R.drawable.turrets_icon;

        } else if (ShipConstant.TYPE_UtilityItems.equals(type)) {
            return R.drawable.utility_items_icon;

        }
        return R.drawable.utility_items_icon;
    }

}
