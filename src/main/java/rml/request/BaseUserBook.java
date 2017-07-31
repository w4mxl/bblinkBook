package rml.request;

public class BaseUserBook  extends BaseNexus {
	private int nexusState;
	private long nexusTime;
	private String typeId;

	//如果有查询条件添加查询条件的字段
	//当前页
	private Integer page =1;
	//每页数
	private Integer size = 10;
	//开始行
	private Integer startRow = 0;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
}
