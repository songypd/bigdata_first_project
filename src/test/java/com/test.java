package com;

import com.bigdata.mapReduce.author.Model;
import org.apache.hadoop.io.WritableComparable;
import org.junit.Test;

/**
 * @author yuanpeng.song
 * @create 2018/10/14
 * @since 1.0.0
 */
public class test {
    @Test
    public void test_001() {
        String demo = "23031488.0$$$$Daniel Souery$$$$ 19804.0$$$$Université Libre de Bruxelles ## Psy Pluriel Centre Européen de Psychologie Médicale$$$$Belgium ## Belgium$$$$药学、制药工业$$$$no elements!$$$$(pubyear-2016)-Pharmacological treatment strategies in unipolar depression in European tertiary psychiatric treatment centers – A pharmacoepidemiological cross-sectional multicenter study$$$$European Neuropsychopharmacology$$$$Elsevier$$$$Bruxelles ## Bruxelles$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$ 4889513.0";
        for (String string : demo.split("\\u0024\\u0024\\u0024\\u0024")) {
            System.out.println(string);
        }
    }


    @Test
    public void test_002() {
        Model m1 = new Model();
        Model m2 = new Model();
        String d1 = " 37464613.0$$$$Xiaofeng Wang$$$$ 19571.0$$$$FrontageTigermed ## Zhejiang Provincial Center for Disease Control and Prevention Department of environmental and occupational health$$$$China$$$$药学、制药工业$$$$no elements!$$$$(pubyear-2017)-Microcystin‐LR induces a wide variety of biochemical changes in the A549 human non‐small cell lung cancer cell line: Roles for protein phosphatase 2A and its substrates$$$$Environmental Toxicology$$$$John Wiley & Sons, Ltd$$$$Hangzhou$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$ 9092691.0";
        String d2 = " 12783436.0$$$$Xiaofeng Wang$$$$ 19806.0$$$$FrontageTigermed$$$$USA ## PR. china$$$$药学、制药工业$$$$xiaofeng.wang@frontagelab.com$$$$(pubyear-2015)-A PBPK model describing a xenobiotic with a short PK event scale$$$$Journal of Pharmacokinetics and Pharmacodynamics$$$$Springer$$$$Exton$$$$no elements!$$$$no elements!$$$$19341$$$$no elements!$$$$[Phone]:(973) 349-8592 [Email]:xiaofeng.wang@frontagelab.com$$$$ 2743465.0\n";
        //        String d2 = " 23011189.0$$$$Kazunori Kadota$$$$ 19748.0$$$$Osaka University of Pharmaceutical Sciences$$$$Japan$$$$药学、制药工业$$$$no elements!$$$$(pubyear-2016)-Application of combinational supercritical CO<ce:inf loc=\"post\">2</ce:inf> techniques to the preparation of inhalable particles$$$$Journal of Drug Delivery Science and Technology$$$$Elsevier$$$$Osaka$$$$no elements!$$$$4-20-1 Nasahara Takatsuki$$$$569-1094$$$$no elements!$$$$no elements!$$$$ 4884893.0\n";

        String[] d1s = d1.split("\\$\\$\\$\\$");
//        String[] d1s = d1.split("\\\\u0024\\\\u0024\\\\u0024\\\\u0024");
        String[] d2s = d2.split("\\$\\$\\$\\$");
        m1.setName(d1s[1]);
        m1.setCountry(d1s[4]);
        m1.setBrands(d1s[3]);
        m2.setName(d2s[1]);
        m2.setCountry(d2s[4]);
        m2.setBrands(d2s[3]);

        System.out.println(compare(m1, m2));
    }

    public int compare(WritableComparable a, WritableComparable b) {

        Model m1 = (Model) a;
        Model m2 = (Model) b;
        // 分组比较器,名称相同，国籍有交集，机构有交集，即为一组数据
        int c1 = Integer.compare(m1.getName().hashCode() & Integer.MAX_VALUE,
                m2.getName().hashCode() & Integer.MAX_VALUE);
        if (c1 == 0) {
            // 判断国籍是否存在交集
            String[] thisCountry = m1.getCountry().split(" ## ");
            String[] thatCountry = m2.getCountry().split(" ## ");
            int c2 = 999;
            for (String s1 : thatCountry) {
                for (String s2 : thisCountry) {
                    if ((0 == Integer.compare(s1.toLowerCase().hashCode() & Integer.MAX_VALUE, s2.toLowerCase().hashCode() & Integer.MAX_VALUE)) ||
                            s1.toLowerCase().contains(s2.toLowerCase())) {
                        return c2 = 0;
                    }
                }
            }
            if (c2 == 0) {
                String[] thisBrands = m1.getBrands().split(" ## ");
                String[] thatBrands = m2.getBrands().split(" ## ");
                // 判断机构是否存在交集
                int c3 = 999;
                for (String s1 : thisBrands) {
                    for (String s2 : thatBrands) {
                        if (0 == Integer.compare(s1.toLowerCase().hashCode() & Integer.MAX_VALUE,
                                s2.toLowerCase().hashCode() & Integer.MAX_VALUE)) {
                            return c3 = 0;
                        }
                    }
                }

                return c3 == 0 ? 0 : -1;
            }
            return c2 == 0 ? 0 : -1;
        }
        return c1;
    }

}