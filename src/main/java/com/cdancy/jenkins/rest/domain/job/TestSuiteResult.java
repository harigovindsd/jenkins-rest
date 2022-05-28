package com.cdancy.jenkins.rest.domain.job;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.json.SerializedNames;

import java.util.List;

@AutoValue
public abstract class TestSuiteResult {

    public abstract List<TestCaseResult> testCaseResults();

    public abstract float duration();

    public abstract List<String> enclosingBlockNames();

    public abstract List<String> enclosingBlocks();

    @Nullable
    public abstract String suiteId();

    public abstract String suiteName();

    @Nullable
    public abstract String nodeId();

    @Nullable
    public abstract String stderr();

    @Nullable
    public abstract String stdout();

    @Nullable
    public abstract String timestamp();

    @SerializedNames({"cases", "duration", "enclosingBlockNames", "enclosingBlocks",
        "id", "name", "nodeId", "stderr", "stdout", "timestamp"})
    TestSuiteResult create(List<TestCaseResult> testCaseResults,
                           float duration,
                           List<String> enclosingBlockNames,
                           List<String> enclosingBlocks,
                           String suiteId,
                           String suiteName,
                           String nodeId,
                           String stderr,
                           String stdout,
                           String timestamp){
        return new AutoValue_TestSuiteResult(
            testCaseResults != null ? ImmutableList.copyOf(testCaseResults) : ImmutableList.<TestCaseResult>of(),
            duration,
            enclosingBlockNames != null ? ImmutableList.copyOf(enclosingBlockNames) : ImmutableList.<String> of(),
            enclosingBlocks != null ? ImmutableList.copyOf(enclosingBlocks) : ImmutableList.<String> of(),
            suiteId, suiteName, nodeId, stderr, stdout, timestamp
        );
    }

}
