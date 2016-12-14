package com.kaiback.crossroad.entity;

/**
 * Created by augustinus on 16/12/14.
 */

public class Event {
    /**
     * 传递到ConversationListFragment的创建单聊事件
     * mTargetId 单聊Id
     * mAppKey 可以得到创建单聊者的AppKey
     */
    public static class StringEvent{
        private String mTargetId;
        private String mAppKey;

        public StringEvent(String targetId, String appKey) {
            this.mTargetId = targetId;
            this.mAppKey = appKey;
        }

        public String getTargetId(){
            return mTargetId;
        }

        public String getAppKey() {
            return mAppKey;
        }
    }

    /**
     * 传递到ConversationListFragment的创建或删除群聊事件
     * mGroupId 群聊Id
     * mIsAddEvent 是否为创建群聊的标志
     */
    public static class LongEvent{
        private long mGroupId;
        private boolean mIsAddEvent;

        public LongEvent(boolean isAddEvent, long groupId){
            this.mIsAddEvent = isAddEvent;
            this.mGroupId = groupId;
        }

        public long getGroupId(){
            return mGroupId;
        }

        public boolean getFlag() {
            return mIsAddEvent;
        }
    }

    /**
     * 传递到ConversationListFragment的保存为草稿的事件
     * mDraft 草稿内容
     */
    public static class DraftEvent {
        private String mTargetId;
        private long mGroupId;
        private String mTargetAppKey;
        private String mDraft;

        public DraftEvent(String targetId, String appKey, String draft) {
            this.mTargetId = targetId;
            this.mTargetAppKey = appKey;
            this.mDraft = draft;
        }

        public DraftEvent(long groupId, String draft) {
            this.mGroupId = groupId;
            this.mDraft = draft;
        }

        public String getTargetId() {
            return mTargetId;
        }


        public long getGroupId() {
            return mGroupId;
        }

        public String getTargetAppKey() {
            return mTargetAppKey;
        }

        public String getDraft() {
            return mDraft;
        }
    }

    public static class AddFriendEvent {
        private long id;

        public AddFriendEvent(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }
    }

}
