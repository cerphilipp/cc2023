package cc2023.teamrandom.ccservice.model;

import java.util.List;
import java.util.Objects;

public class MastodonAccount {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MastodonAccount that = (MastodonAccount) o;
		return Objects.equals(id, that.id) && Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username);
	}
}