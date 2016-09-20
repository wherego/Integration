package vip.xuanhao.integration.presenters.common;

/**
 * Created by Xuanhao on 2016/8/18.
 */

public class Events {

    public static class ConnectionEvent {

        private boolean isConnection;

        public ConnectionEvent(boolean isConnection) {
            this.isConnection = isConnection;
        }

        public boolean isConnection() {
            return isConnection;
        }

        public void setConnection(boolean connection) {
            isConnection = connection;
        }
    }

}
