package in.multico.connector;

import org.json.JSONObject;

/**
 * Copyright © 2016 Marat Shmush. All rights reserved.
 */

public class Coincap extends ConnectorBased {

    public static final String URL = "http://coincap.io/page/";

    public static void getPrice(final String coin, final PriceListener pl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = get(URL + coin);
                    JSONObject jo = new JSONObject(s);
                    pl.onPrice(jo.getDouble("usdPrice"));
                } catch (Exception e) {
                    e.printStackTrace();
                    pl.onPrice(0.0);
                }
            }
        }).start();
    }

    public interface PriceListener {
        void onPrice(double price);
    }
}