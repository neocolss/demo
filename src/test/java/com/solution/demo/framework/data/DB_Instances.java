package com.solution.demo.framework.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle multiple connections to databases
 */
public class DB_Instances {

    static List<DB> dbs = new ArrayList<>();

    public static DB get(String environnement) {
        for (DB db : dbs) {
            if (environnement.equalsIgnoreCase(db.getEnv())) {
                return db;
            }
        }
        DB db_ = new DB(environnement);
        dbs.add(db_);
        return db_;
    }
}
