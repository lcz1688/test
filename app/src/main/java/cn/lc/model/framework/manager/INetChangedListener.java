package cn.lc.model.framework.manager;

public interface INetChangedListener {
	public void onNetChanged(boolean oldStatus, boolean newStatus);
}
