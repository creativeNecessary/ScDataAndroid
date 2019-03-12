package sc.com.commonlib.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2018/3/28.
 */

public class ShipConstant {

    //TAG
    //航空电子设备
    public static String TAG_AVIONIC = "avionic";
    //模块
    public static String TAG_MODULAR = "modular";
    //推进设备
    public static String TAG_PROPULSION = "propulsion";
    //推进器
    public static String TAG_THRUSTER = "thruster";
    //武器
    public static String TAG_WEAPON = "weapon";


    public static String TYPE_Radar = "radar";
    public static String TYPE_Computers = "computers";
    public static String TYPE_PowerPlants = "power_plants";
    public static String TYPE_Coolers = "coolers";
    public static String TYPE_ShieldGenerators= "shield_generators";
    public static String TYPE_FuelIntakes= "fuel_intakes";
    public static String TYPE_FuelTanks= "fuel_tanks";
    public static String TYPE_QuantumDrives= "quantum_drives";
    public static String TYPE_JumpModules= "jump_modules";
    public static String TYPE_QuantumFuelTanks= "quantum_fuel_tanks";
    public static String TYPE_MainThrusters= "main_thrusters";
    public static String TYPE_ManeuveringThrusters= "maneuvering_thrusters";
    public static String TYPE_Weapons= "weapons";
    public static String TYPE_Missiles= "missiles";
    public static String TYPE_Turrets= "turrets";
    public static String TYPE_UtilityItems= "utility_items";


    //武器
    public static String Avionics = "航空电子器材";
    public static String Systems = "系统";
    public static String Propulsion = "推进设备";
    public static String Thruster = "推进器";
    public static String Weapons = "武器";


    public static String name = "名称";
    public static String description = "简要介绍";
    public static String size = "尺寸";
    public static String focus = "定位";
    public static String max_crew = "最大船员";
    public static String min_crew = "最小船员";
    public static String pitch_max = "绕Y轴转动速度";
    public static String yaw_max = "绕Z轴转动速度";
    public static String roll_max = "绕X轴转动速度";
    public static String x_axis_acceleration = "X轴加速度";
    public static String y_axis_acceleration = "Y轴加速度";
    public static String z_axis_acceleration = "Z轴加速度";
    public static String cargo_capacity = "载货量";
    public static String mass = "飞船质量";
    public static String scm_speed = "子巡航速度";
    public static String afterburner_speed = "加力燃烧速度";
    public static String length = "长";
    public static String beam = "宽";
    public static String height = "高";

    public static List<String> equipmentTypeList = new ArrayList<>();
    public static List<String> equipmentTypeChList = new ArrayList<>();

    public static String getEquipmentTypeList(String equipment){
        if(equipmentTypeList.size() == 0 || equipmentTypeChList.size() == 0){
            equipmentTypeList.clear();
            equipmentTypeChList.clear();

            equipmentTypeList.add(TYPE_Radar);
            equipmentTypeList.add(TYPE_Computers);
            equipmentTypeList.add(TYPE_PowerPlants);
            equipmentTypeList.add(TYPE_Coolers);
            equipmentTypeList.add(TYPE_ShieldGenerators);
            equipmentTypeList.add(TYPE_FuelIntakes);
            equipmentTypeList.add(TYPE_FuelTanks);
            equipmentTypeList.add(TYPE_QuantumDrives);
            equipmentTypeList.add(TYPE_JumpModules);
            equipmentTypeList.add(TYPE_QuantumFuelTanks);
            equipmentTypeList.add(TYPE_MainThrusters);
            equipmentTypeList.add(TYPE_ManeuveringThrusters);
            equipmentTypeList.add(TYPE_Weapons);
            equipmentTypeList.add(TYPE_Missiles);
            equipmentTypeList.add(TYPE_Turrets);
            equipmentTypeList.add(TYPE_UtilityItems);

            equipmentTypeChList.add("雷达");
            equipmentTypeChList.add("计算机");
            equipmentTypeChList.add("发电装置");
            equipmentTypeChList.add("冷却装置");
            equipmentTypeChList.add("护盾发生器");
            equipmentTypeChList.add("喷油器");
            equipmentTypeChList.add("燃料箱");
            equipmentTypeChList.add("量子引擎");
            equipmentTypeChList.add("跳跃模块");
            equipmentTypeChList.add("量子燃料箱");
            equipmentTypeChList.add("主推进器");
            equipmentTypeChList.add("辅助推进器");
            equipmentTypeChList.add("武器");
            equipmentTypeChList.add("导弹");
            equipmentTypeChList.add("炮塔");
            equipmentTypeChList.add("功能扩展");

        }

        int index = equipmentTypeList.indexOf(equipment);
        if(index == -1 ){
            return equipment;
        }

        return equipmentTypeChList.get(index);
    }





}
