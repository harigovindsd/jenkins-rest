package com.cdancy.jenkins.rest.domain.job;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import org.jclouds.json.SerializedNames;

import java.util.List;

@AutoValue
public abstract class TestClassResult {

    public abstract List<TestCaseResult> testCaseResults();

    public abstract int failCount();

    public abstract String testClassName();

    public abstract int passCount();

    public abstract int skipCount();

    @SerializedNames({"child", "failCount", "name", "passCount", "skipCount"})
    public TestClassResult create(List<TestCaseResult> testCaseResults,
                                 String testClassName,
                                 int failCount,
                                 int passCount,
                                 int skipCount){
        return new AutoValue_TestClassResult(
            testCaseResults != null ? ImmutableList.copyOf(testCaseResults) : ImmutableList.<TestCaseResult> of(),
            failCount, testClassName, passCount, skipCount
        );
    }
}
