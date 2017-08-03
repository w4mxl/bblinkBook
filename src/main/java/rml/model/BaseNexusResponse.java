package rml.model;

/**
 * Created by bblink on 2017/5/11.
 */
public class BaseNexusResponse {

    private int nexusState;
    private long nexusTime;

    public long getNexusTime() {
        return nexusTime;
    }

    public void setNexusTime(long nexusTime) {
        this.nexusTime = nexusTime;
    }

    private String typeId;

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
}
