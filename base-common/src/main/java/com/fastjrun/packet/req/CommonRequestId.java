package com.fastjrun.packet.req;

public class CommonRequestId {
    
    private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return "CommonRequestId [id=" + id + ", getId()=" + getId() + "]";
    }

}
