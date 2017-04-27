package rml.request;

public class BaseUserBook  extends BaseNexus{
	private int nexusState;
	private long nexusTime;
	public long getNexusTime() {
		return nexusTime;
	}
	public void setNexusTime(long nexusTime) {
		this.nexusTime = nexusTime;
	}
	public int getNexusState() {
		return nexusState;
	}
	public void setNexusState(int nexusState) {
		this.nexusState = nexusState;
	}

}
