package com.cdancy.jenkins.rest.domain.job;

import com.google.auto.value.AutoValue;
import org.jclouds.json.SerializedNames;

import java.util.List;

@AutoValue
public abstract class TestReport {

    public abstract String duration();

    public abstract boolean isEmpty();

    public abstract int failCount();

    public abstract int passCount();

    public abstract int skipCount();

    public abstract List<TestSuiteResult> testSuiteResults();

    @SerializedNames({"duration", "empty", "failCount", "passCount", "skipCount", "suites"})
    TestReport create(String duration, boolean isEmpty, int failCount, int passCount, int skipCount, List<TestSuiteResult> testSuiteResults){
        return new AutoValue_TestReport(
            duration, isEmpty, failCount, passCount, skipCount, testSuiteResults
        );
    }

}
