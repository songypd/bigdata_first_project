package com.bigdata.common;

/**
 * Created by yuanpeng.song on 2018/9/12.
 */
public enum ConstantEnums {
    dongcheng(1,"东城"),
    fengtai(2,"丰台"),
    yizhuang(3,"亦庄开发区"),
    daxing(4,"大兴"),
    fangshan(5,"房山"),
    changping(6,"昌平"),
    chaoyang(7,"朝阳"),
    haidian(8,"海淀"),
    shijingshan(9,"石景山"),
    xicheng(10,"西城"),
    tongzhou(11,"通州"),
    mentougou(12,"门头沟"),
    shunyi(13,"顺义");

    int code;
    String content;

    public static ConstantEnums getByContent(String content){
        for (ConstantEnums c :ConstantEnums.values()){
            if (c.getContent().equals(content)){
                return c;
            }
        }
        return null;
    }

    public static ConstantEnums getByCode(int  code){
        for (ConstantEnums c :ConstantEnums.values()){
            if (c.code == code){
                return c;
            }
        }
        return null;
    }

    ConstantEnums(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }
}
