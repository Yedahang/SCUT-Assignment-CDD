package Model.Entity;

public class Title {

    private int Id; //称号编号
    private String name; // 称号名称
    private String description; // 称号描述

    // 构造函数
    public Title(int id,String name, String description) {
        this.Id=id;
        this.name = name;
        this.description = description;
    }

    // 获取称号名称
    public String Id_Name(int id) {
        for (Title title : TITLES) {
            if (title.getId() == id) {
                return title.getName();
            }
        }
        return null; // 如果没有找到对应id的Title，则返回null或者你认为合适的默认值
    }

    private String getName() {
        return this.name;
    }

    private int getId() {
        return this.Id;
    }


    // 获取称号描述
    public String getDescription() {
        return description;
    }

    // 称号列表
    public static final Title[] TITLES = {
            new Title(0,"平平无奇","未获得任何成就"),
            new Title(1,"初尝胜果", "赢得1局游戏"),
            new Title(10,"小有所成", "赢得10局游戏"),
            new Title(100,"游戏达人", "赢得100局游戏"),
            new Title(200,"顺风顺水", "连胜5局"),
            new Title(300,"常胜将军", "连胜10局"),
            new Title(400,"打酱油的", "在一局游戏中，一张牌也没有出"),
            new Title(800,"行云流水", "同花顺五张")
    };
}