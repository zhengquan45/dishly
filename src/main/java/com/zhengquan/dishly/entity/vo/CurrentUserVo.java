package com.zhengquan.dishly.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CurrentUserVo {
    private Data data;
    private String errorCode;
    private String errorMessage;
    private boolean success;
    @lombok.Data
    @Accessors(chain = true)
    public static class Data {
        private String name;
        private String avatar;
        private String userid;
        private String email;
        private String signature;
        private String title;
        private String group;
        private List<Tag> tags;
        private int notifyCount;
        private int unreadCount;
        private String country;
        private String access;
        private Geographic geographic;
        private String address;
        private String phone;
        @lombok.Data
        @Accessors(chain = true)
        public static class Tag {
            private String key;
            private String label;
        }
        @lombok.Data
        @Accessors(chain = true)
        public static class Geographic {
            private Province province;
            private City city;
            @lombok.Data
            @Accessors(chain = true)
            public static class Province {
                private String label;
                private String key;

                // Getters and Setters
            }
            @lombok.Data
            @Accessors(chain = true)
            public static class City {
                private String label;
                private String key;

                // Getters and Setters
            }
        }
    }
}
