package com.liveproject.persi.ycce.iimp;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Tiger on 22-08-2016.
 */
public class MemberService {
    Member u;
    Member[] m = new Member[5];

    public Member getUser() {
       // u = new Member("1", "Demo Name", "demo@demo.com", "9876543210", "Principal");
        return u;
    }

    public void createMember() {
/*
        m[0] = new Member("1", "Ved", "demo@demo.com", "9876543210", "Principal");
        m[1] = new Member("2", "Akash", "demo@demo.com", "9876543210", "Principal");
        m[2] = new Member("3", "Aakash", "demo@demo.com", "9876543210", "Principal");
        m[3] = new Member("4", "PrasadZ", "demo@demo.com", "9876543210", "Principal");
        m[4] = new Member("5", "Mayur", "demo@demo.com", "9876543210", "Principal");
*/    }

    public String displayMember(Member[] mt) {
        String list = "";
        for (int counter = 0; counter < mt.length; counter++) {
            list = list + mt[counter].getFirstname() + ",  " + mt[counter].getMobileno() + "\n";
        }
        return list;
    }

    public Member[] getAll() {
        this.createMember();
        return m;
    }

    //For creating a new member object

    public Member createMember(JSONArray jsonarray) {
            return u;
    }

    //For the Json Fetch
   public Member fetchUserData(JSONArray jsonarray){
       try {
           for (int i = 0; i < jsonarray.length(); i++) {
               JSONObject jsonobject = jsonarray.getJSONObject(i);
               String firstName = jsonobject.getString("FirstName");
               String id = jsonobject.getString("Id");
               String Emailid = jsonobject.getString("Email");
               u.setFirstname(firstName);
               u.setEmailid(Emailid);
               u.setId(id);
               break;
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
        return u;

        }
}