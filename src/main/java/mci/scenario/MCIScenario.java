package mci.scenario;

import kr.ac.kaist.se.simulator.BaseAction;
import kr.ac.kaist.se.simulator.BaseConstituent;
import kr.ac.kaist.se.simulator.BaseScenario;
import kr.ac.kaist.se.simulator.Environment;
import mci.model.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * MCIScenario.java
 * Author: Junho Kim <jhkim@se.kaist.ac.kr>

 * The MIT License (MIT)

 * Copyright (c) 2016 Junho Kim

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions: TBD
 */

public class MCIScenario extends BaseScenario {

    private ArrayList<BaseConstituent> csList;
    private BaseConstituent manager;
    private Environment env;
    private ArrayList<BaseAction> actionList;

    public MCIScenario(){
        NearestPTS np1 = new NearestPTS();
        NearestPTS np2 = new NearestPTS();
        SeverityPTS sp1 = new SeverityPTS();
        SeverityPTS sp2 = new SeverityPTS();
        BaseConstituent[] CSs = new BaseConstituent[]{np1, np2, sp1, sp2};

        this.csList = new ArrayList<>();
        this.csList.addAll(Arrays.asList(CSs));
        this.manager = new Hospital();

        this.actionList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            this.actionList.add(new RescueAction(50, 0));

        this.env = new Environment(CSs, this.actionList.toArray(new BaseAction[this.actionList.size()]));

    }

    @Override
    public void init() {
        for (int i = 0; i <= 100; i++) {
            Hospital.GeoMap.add(new MapPoint(i));
        }
    }

    @Override
    public String getDescription() { return "MCI (4 PTSs)"; }

    @Override
    public ArrayList<BaseConstituent> getCSList() {
        return csList;
    }

    @Override
    public BaseConstituent getManager() {
        return this.manager;
    }

    @Override
    public void setManager(BaseConstituent manager){
        this.manager = manager;
    }

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    @Override
    public Environment getEnvironment() {
        return env;
    }

    @Override
    public ArrayList<BaseAction> getActionList() {
        return this.actionList;
    }

    @Override
    public void setActionList(ArrayList<BaseAction> aList) {
        this.actionList = aList;
    }

    @Override
    public void setCSList(BaseConstituent[] CSs){
        if(csList != null)
            csList.clear();
        else
            this.csList = new ArrayList<>();
        this.csList.addAll(Arrays.asList(CSs));
    }
}
