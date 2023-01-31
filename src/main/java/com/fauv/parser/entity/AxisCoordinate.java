package com.fauv.parser.entity;

import com.fauv.parser.entity.enums.AxisType;

public abstract class AxisCoordinate {

	private String name;
	private Double lowerTolerance;
	private Double superiorTolerance;
	private AxisType axis;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Double getLowerTolerance() {
		return lowerTolerance;
	}

	public void setLowerTolerance(Double lowerTolerance) {
		this.lowerTolerance = lowerTolerance;
	}

	public Double getSuperiorTolerance() {
		return superiorTolerance;
	}

	public void setSuperiorTolerance(Double superiorTolerance) {
		this.superiorTolerance = superiorTolerance;
	}

	public AxisType getAxis() {
		return axis;
	}

	public void setAxis(AxisType axis) {
		this.axis = axis;
	}
	
	public void setTolerance(Double tolerance) {
		if (this.bothToleranceAreSet() || tolerance == null) { return; }
		
		if (this.bothToleranceAreNotSet()) {
			this.setSuperiorTolerance(tolerance);
			return;
		}
		
		boolean changeSuperiorToleranceForNewOne = tolerance > this.getSuperiorTolerance();
		
		if (changeSuperiorToleranceForNewOne) {
			this.setLowerTolerance(new Double(this.getSuperiorTolerance()));
			this.setSuperiorTolerance(tolerance);
		}else {
			this.setLowerTolerance(tolerance);
		}
	
	}
	
	private boolean bothToleranceAreNotSet() {
		return this.getLowerTolerance() == null && this.getSuperiorTolerance() == null;
	}
	
	private boolean bothToleranceAreSet() {
		return this.getLowerTolerance() != null && this.getSuperiorTolerance() != null;
	}

}
