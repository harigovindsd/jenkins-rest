package com.cdancy.jenkins.rest.domain.job;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import org.jclouds.json.SerializedNames;

import java.util.List;

@AutoValue
public abstract class TestPackageResult {

    public abstract String testPackageName();

    public abstract List<TestClassResult> testClassResults();

    public abstract int passCount();

    public abstract int failCount();

    public abstract int skipCount();

    @SerializedNames({"name", "child", "passCount", "failCount", "skipCount"})
    TestPackageResult create(String testPackageName,List<TestClassResult> testClassResults, int passCount, int failCount, int skipCount ){
        return new AutoValue_TestPackageResult(
            testPackageName,
            testClassResults != null ? ImmutableList.copyOf(testClassResults) : ImmutableList.of(),
            passCount, failCount, skipCount
        );
    }

}
