public enum SpecialityEnum {

    SING("唱歌"),DANCE("跳舞"),SWIMMING("游泳"),RUNNING("跑步");
    private String desc;

    SpecialityEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
}
