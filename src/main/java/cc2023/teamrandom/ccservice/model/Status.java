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

        public String getUsername() {
                return account.getUsername();
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

        @Override
        public String toString() {
                return "Status{" +
                        "id='" + id + '\'' +
                        ", uri='" + uri + '\'' +
                        ", createdAt=" + createdAt +
                        ", account=" + account +
                        ", content='" + content + '\'' +
                        ", visibility='" + visibility + '\'' +
                        ", spoilerText='" + spoilerText + '\'' +
                        ", mediaAttachments=" + mediaAttachments +
                        ", application=" + application +
                        ", mentions=" + mentions +
                        ", tags=" + tags +
                        ", reblogsCount=" + reblogsCount +
                        ", favouritesCount=" + favouritesCount +
                        ", repliesCount=" + repliesCount +
                        ", url='" + url + '\'' +
                        ", inReplyToId='" + inReplyToId + '\'' +
                        ", inReplyToAccountId='" + inReplyToAccountId + '\'' +
                        ", reblog=" + reblog +
                        ", poll=" + poll +
                        ", card=" + card +
                        ", language='" + language + '\'' +
                        ", text=" + text +
                        ", editedAt=" + editedAt +
                        ", pinned=" + pinned +
                        ", muted=" + muted +
                        ", reblogged=" + reblogged +
                        ", sensitive=" + sensitive +
                        ", bookmarked=" + bookmarked +
                        ", favourited=" + favourited +
                        '}';
        }
}




