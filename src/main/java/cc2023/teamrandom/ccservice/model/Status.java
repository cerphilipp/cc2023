package cc2023.teamrandom.ccservice.model;

import java.time.ZonedDateTime;
import java.util.List;

import cc2023.teamrandom.ccservice.model.MastodonCreatedAt;
import java.util.List;

public class Status {
        private String id;
        private String uri;
        public MastodonCreatedAt createdAt;
        private MastodonAccount account;
        private String content;
        private String visibility;
        private String spoilerText;
        private List<Object> mediaAttachments;
        private MastodonApplication application;
        private List<Object> mentions;
        private List<Object> tags;
        private int reblogsCount;
        private int favouritesCount;
        private int repliesCount;
        private String url;
        private String inReplyToId;
        private String inReplyToAccountId;
        private Object reblog;
        private Object poll;
        private Object card;
        private String language;
        private Object text;
        private MastodonCreatedAt editedAt;
        private boolean pinned;
        private boolean muted;
        private boolean reblogged;
        private boolean sensitive;
        private boolean bookmarked;
        private boolean favourited;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getUri() {
                return uri;
        }

        public void setUri(String uri) {
                this.uri = uri;
        }

        public MastodonCreatedAt getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(MastodonCreatedAt createdAt) {
                this.createdAt = createdAt;
        }

        public MastodonAccount getAccount() {
                return account;
        }

        public void setAccount(MastodonAccount account) {
                this.account = account;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public String getVisibility() {
                return visibility;
        }

        public void setVisibility(String visibility) {
                this.visibility = visibility;
        }

        public String getSpoilerText() {
                return spoilerText;
        }

        public void setSpoilerText(String spoilerText) {
                this.spoilerText = spoilerText;
        }

        public List<Object> getMediaAttachments() {
                return mediaAttachments;
        }

        public void setMediaAttachments(List<Object> mediaAttachments) {
                this.mediaAttachments = mediaAttachments;
        }

        public MastodonApplication getApplication() {
                return application;
        }

        public void setApplication(MastodonApplication application) {
                this.application = application;
        }

        public List<Object> getMentions() {
                return mentions;
        }

        public void setMentions(List<Object> mentions) {
                this.mentions = mentions;
        }

        public List<Object> getTags() {
                return tags;
        }

        public void setTags(List<Object> tags) {
                this.tags = tags;
        }

        public int getReblogsCount() {
                return reblogsCount;
        }

        public void setReblogsCount(int reblogsCount) {
                this.reblogsCount = reblogsCount;
        }

        public int getFavouritesCount() {
                return favouritesCount;
        }

        public void setFavouritesCount(int favouritesCount) {
                this.favouritesCount = favouritesCount;
        }

        public int getRepliesCount() {
                return repliesCount;
        }

        public void setRepliesCount(int repliesCount) {
                this.repliesCount = repliesCount;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getInReplyToId() {
                return inReplyToId;
        }

        public void setInReplyToId(String inReplyToId) {
                this.inReplyToId = inReplyToId;
        }

        public String getInReplyToAccountId() {
                return inReplyToAccountId;
        }

        public void setInReplyToAccountId(String inReplyToAccountId) {
                this.inReplyToAccountId = inReplyToAccountId;
        }

        public Object getReblog() {
                return reblog;
        }

        public void setReblog(Object reblog) {
                this.reblog = reblog;
        }

        public Object getPoll() {
                return poll;
        }

        public void setPoll(Object poll) {
                this.poll = poll;
        }

        public Object getCard() {
                return card;
        }

        public void setCard(Object card) {
                this.card = card;
        }

        public String getLanguage() {
                return language;
        }

        public void setLanguage(String language) {
                this.language = language;
        }

        public Object getText() {
                return text;
        }

        public void setText(Object text) {
                this.text = text;
        }

        public MastodonCreatedAt getEditedAt() {
                return editedAt;
        }

        public void setEditedAt(MastodonCreatedAt editedAt) {
                this.editedAt = editedAt;
        }

        public boolean isPinned() {
                return pinned;
        }

        public void setPinned(boolean pinned) {
                this.pinned = pinned;
        }

        public boolean isMuted() {
                return muted;
        }

        public void setMuted(boolean muted) {
                this.muted = muted;
        }

        public boolean isReblogged() {
                return reblogged;
        }

        public void setReblogged(boolean reblogged) {
                this.reblogged = reblogged;
        }

        public boolean isSensitive() {
                return sensitive;
        }

        public void setSensitive(boolean sensitive) {
                this.sensitive = sensitive;
        }

        public boolean isBookmarked() {
                return bookmarked;
        }

        public void setBookmarked(boolean bookmarked) {
                this.bookmarked = bookmarked;
        }

        public boolean isFavourited() {
                return favourited;
        }

        public void setFavourited(boolean favourited) {
                this.favourited = favourited;
        }

        // Getter und Setter hier einfügen

}

class MastodonCreatedAt {
        private String instant;
        private boolean valid;

        public String getInstant() {
                return instant;
        }

        public void setInstant(String instant) {
                this.instant = instant;
        }

        public boolean isValid() {
                return valid;
        }

        public void setValid(boolean valid) {
                this.valid = valid;
        }

        // Getter und Setter hier einfügen

}
class MastodonApplication {
        private String name;
        private Object website;
        private String vapidKey;
        private Object clientId;
        private Object clientSecret;

        // Getter und Setter hier einfügen

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Object getWebsite() {
                return website;
        }

        public void setWebsite(Object website) {
                this.website = website;
        }

        public String getVapidKey() {
                return vapidKey;
        }

        public void setVapidKey(String vapidKey) {
                this.vapidKey = vapidKey;
        }

        public Object getClientId() {
                return clientId;
        }

        public void setClientId(Object clientId) {
                this.clientId = clientId;
        }

        public Object getClientSecret() {
                return clientSecret;
        }

        public void setClientSecret(Object clientSecret) {
                this.clientSecret = clientSecret;
        }
}

class MastodonAccount {
        private String id;
        private String username;
        private String acct;
        private String url;
        private String displayName;
        private String note;
        private String avatar;
        private String avatarStatic;
        private String header;
        private String headerStatic;
        private List<Object> fields;
        private List<Object> emojis;
        private Object moved;
        private MastodonCreatedAt createdAt;
        private MastodonCreatedAt lastStatusAt;
        private int statusesCount;
        private int followersCount;
        private int followingCount;
        private Object limited;
        private boolean locked;
        private boolean group;
        private Object suspended;
        private boolean bot;
        private Object discoverable;
        private boolean notIndexed;

        // Getter und Setter hier einfügen

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getAcct() {
                return acct;
        }

        public void setAcct(String acct) {
                this.acct = acct;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getDisplayName() {
                return displayName;
        }

        public void setDisplayName(String displayName) {
                this.displayName = displayName;
        }

        public String getNote() {
                return note;
        }

        public void setNote(String note) {
                this.note = note;
        }

        public String getAvatar() {
                return avatar;
        }

        public void setAvatar(String avatar) {
                this.avatar = avatar;
        }

        public String getAvatarStatic() {
                return avatarStatic;
        }

        public void setAvatarStatic(String avatarStatic) {
                this.avatarStatic = avatarStatic;
        }

        public String getHeader() {
                return header;
        }

        public void setHeader(String header) {
                this.header = header;
        }

        public String getHeaderStatic() {
                return headerStatic;
        }

        public void setHeaderStatic(String headerStatic) {
                this.headerStatic = headerStatic;
        }

        public List<Object> getFields() {
                return fields;
        }

        public void setFields(List<Object> fields) {
                this.fields = fields;
        }

        public List<Object> getEmojis() {
                return emojis;
        }

        public void setEmojis(List<Object> emojis) {
                this.emojis = emojis;
        }

        public Object getMoved() {
                return moved;
        }

        public void setMoved(Object moved) {
                this.moved = moved;
        }

        public MastodonCreatedAt getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(MastodonCreatedAt createdAt) {
                this.createdAt = createdAt;
        }

        public MastodonCreatedAt getLastStatusAt() {
                return lastStatusAt;
        }

        public void setLastStatusAt(MastodonCreatedAt lastStatusAt) {
                this.lastStatusAt = lastStatusAt;
        }

        public int getStatusesCount() {
                return statusesCount;
        }

        public void setStatusesCount(int statusesCount) {
                this.statusesCount = statusesCount;
        }

        public int getFollowersCount() {
                return followersCount;
        }

        public void setFollowersCount(int followersCount) {
                this.followersCount = followersCount;
        }

        public int getFollowingCount() {
                return followingCount;
        }

        public void setFollowingCount(int followingCount) {
                this.followingCount = followingCount;
        }

        public Object getLimited() {
                return limited;
        }

        public void setLimited(Object limited) {
                this.limited = limited;
        }

        public boolean isLocked() {
                return locked;
        }

        public void setLocked(boolean locked) {
                this.locked = locked;
        }

        public boolean isGroup() {
                return group;
        }

        public void setGroup(boolean group) {
                this.group = group;
        }

        public Object getSuspended() {
                return suspended;
        }

        public void setSuspended(Object suspended) {
                this.suspended = suspended;
        }

        public boolean isBot() {
                return bot;
        }

        public void setBot(boolean bot) {
                this.bot = bot;
        }

        public Object getDiscoverable() {
                return discoverable;
        }

        public void setDiscoverable(Object discoverable) {
                this.discoverable = discoverable;
        }

        public boolean isNotIndexed() {
                return notIndexed;
        }

        public void setNotIndexed(boolean notIndexed) {
                this.notIndexed = notIndexed;
        }
}
