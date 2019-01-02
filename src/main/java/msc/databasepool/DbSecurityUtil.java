package msc.databasepool;

import java.util.*;

/**
 * Created by tony on 2017/12/27.
 */
public class DbSecurityUtil {

    private List<EncryptField> getEncFields(DbEnum dbEnum) {
        return encFields.get(dbEnum);
    }

    public static void dec(List<Map<String, Object>> datas) {
        if (datas != null) {
            Map<String, List<String>> mobileMap = new HashMap<>();
            Map<String, List<String>> idcardMap = new HashMap<>();
            Map<String, List<String>> bankHolderMap = new HashMap<>();
            Map<String, List<String>> bankAccountMap = new HashMap<>();
            int index = 0;
            for (Map<String, Object> data : datas) {
                for (String key : data.keySet()) {
                    if (isMobileField(key)) {
                        getByKey(mobileMap, key).add((String) data.get(key));
                    } else if (isIdcardField(key)) {
                        getByKey(idcardMap, key).add((String) data.get(key));
                    } else if (isBankHolderField(key)) {
                        getByKey(bankHolderMap, key).add((String) data.get(key));
                    } else if (isBankAccountField(key)) {
                        getByKey(bankAccountMap, key).add((String) data.get(key));
                    }
                }
                index++;
            }

            for (String field : mobileMap.keySet()) {
                mobileMap.put(field, EncDecServ.decMobiles(mobileMap.get(field)));
            }
            for (String field : idcardMap.keySet()) {
                idcardMap.put(field, EncDecServ.decIdcs(idcardMap.get(field)));
            }
            for (String field : bankHolderMap.keySet()) {
                bankHolderMap.put(field, EncDecServ.decNames(bankHolderMap.get(field)));
            }
            for (String field : bankAccountMap.keySet()) {
                bankAccountMap.put(field, EncDecServ.decBankCards(bankAccountMap.get(field)));
            }

            index = 0;
            for (Map<String, Object> data : datas) {
                for (String key : data.keySet()) {
                    if (isMobileField(key)) {
                        data.put(key, mobileMap.get(key).get(index));
                    } else if (isIdcardField(key)) {
                        data.put(key, idcardMap.get(key).get(index));
                    } else if (isBankHolderField(key)) {
                        data.put(key, bankHolderMap.get(key).get(index));
                    } else if (isBankAccountField(key)) {
                        data.put(key, bankAccountMap.get(key).get(index));
                    }
                }
                index++;
            }
        }
        return;
    }

    public static void dec(Map<String, Object> data) {
        if (data != null) {
            for (String s : data.keySet()) {
                if (isMobileField(s)) {
                    data.put(s, EncDecServ.decMobile((String) data.get(s)));
                } else if (isIdcardField(s)) {
                    data.put(s, EncDecServ.decIdc((String) data.get(s)));
                } else if (isBankHolderField(s)) {
                    data.put(s, EncDecServ.decName((String) data.get(s)));
                } else if (isBankAccountField(s)) {
                    data.put(s, EncDecServ.decBankCard((String) data.get(s)));
                }
            }
        }
        return;
    }

    private static boolean isIdcardField(String columnName) {
        return columnName.equalsIgnoreCase("F_idcard") || columnName.equalsIgnoreCase("idcard") || columnName.equalsIgnoreCase("fIdcard");
    }

    private static boolean isBankHolderField(String columnName) {
        return columnName.equalsIgnoreCase("F_bank_holder") || columnName.equalsIgnoreCase("bank_holder") || columnName.equalsIgnoreCase("fBankHolder");
    }

    private static boolean isBankAccountField(String columnName) {
        return columnName.equalsIgnoreCase("F_bank_account") || columnName.equalsIgnoreCase("bank_account") || columnName.equalsIgnoreCase("fBankAccount");
    }

    /**
     * 是否加密字段
     *
     * @param columnName
     * @return
     */
    private static boolean isMobileField(String columnName) {
        return encFieldsSet.contains(columnName.toLowerCase());
    }

    private static List<String> getByKey(Map<String, List<String>> contain, String key) {
        List<String> lists = contain.get(key);
        if (lists == null) {
            lists = new ArrayList<>();
            contain.put(key, lists);
        }
        return lists;
    }


    private static final Map<DbEnum, List<EncryptField>> encFields = new HashMap<>();
    private static final Set<String> encFieldsSet = new HashSet<>();

    static {
        List<EncryptField> fields = new ArrayList<>();
        fields.add(new EncryptField(DbEnum.lepos, "t_merchant_tmp", "F_mobile,F_bankcard_phone"));
        fields.add(new EncryptField(DbEnum.lepos, "t_merchant_xx", "F_bankcard_phone"));
        fields.add(new EncryptField(DbEnum.lepos, "t_user_xx", "F_mobile"));

        fields.add(new EncryptField(DbEnum.lepos_business, "t_merchant", "F_bankcard_phone"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_user", "F_mobile"));

        fields.add(new EncryptField(DbEnum.lepos_business, "t_merchant_no_agent_tmp", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_merchant_no_agent_tmp_center", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_table", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_table", "F_regist_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_table_auto", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_table_auto", "F_regist_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_table_auto", "F_from_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_table_autoF4", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_pass_by_mobile", "F_apply_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_pass_by_mobile", "F_pass_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_online_sale_order", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_shop_order", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_business_order", "F_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_ditui_merchant", "F_merchant_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_ditui_merchant", "F_merchant_bank_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_ditui_merchant_tmp", "F_merchant_mobile"));
        fields.add(new EncryptField(DbEnum.lepos_business, "t_ditui_merchant_tmp", "F_merchant_bank_mobile"));

        encFieldsSet.add("mobile");
        for (EncryptField field : fields) {
            String tableFiled = field.getField();
            for (String tmpField : tableFiled.split(",")) {
                encFieldsSet.add(tmpField.toLowerCase());
            }
            List<EncryptField> dbFields = encFields.get(field.getDb());
            if (dbFields == null) {
                dbFields = new ArrayList<>();
                encFields.put(field.getDb(), dbFields);
            }
            dbFields.add(field);
        }
    }


    public enum DbEnum {
        lepos,
        lepos_business
    }

    public static class EncryptField {
        private DbEnum db;
        private String table;
        private String field;

        public EncryptField(DbEnum db, String table, String field) {
            this.db = db;
            this.table = table;
            this.field = field;
        }

        public DbEnum getDb() {
            return db;
        }

        public String getTable() {
            return table;
        }

        public String getField() {
            return field;
        }
    }


}
