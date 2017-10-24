package com.myswing.provider;


import org.springframework.beans.factory.annotation.Autowired;

import com.myswing.api.VersionUpgradeService;
import com.myswing.model.VersionUpgrade;
import com.myswing.service.VersionUpgradeBaseService;

public class VersionUpgradeServiceImpl implements VersionUpgradeService {
	@Autowired
	private VersionUpgradeBaseService versionUpgradeService;
    public String checkVersion(String version) {
    	VersionUpgrade versioncode = versionUpgradeService.getVersionUpgrade(version);
    	System.out.println(versioncode.getVersionCode());
        return "current version is "+versioncode.getVersionCode();
    }

}