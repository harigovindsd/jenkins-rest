package com.cdancy.jenkins.rest.domain.job;

import com.google.auto.value.AutoValue;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.json.SerializedNames;

@AutoValue
public abstract class TestCaseResult {

    public abstract int age();

    public abstract String className();

    public abstract float duration();

    @Nullable
    public abstract String errorDetails();

    @Nullable
    public abstract String errorStackTrace();

    public abstract int failedSince();

    public abstract String testCaseName();

    public abstract boolean skipped();

    @Nullable
    public abstract String skippedMessage();

    public abstract String status();

    @Nullable
    public abstract String stderr();

    @Nullable
    public abstract String stdout();


    @SerializedNames(
        {
            "age", "className", "duration", "errorDetails", "errorStackTrace", "failedSince",
            "name", "skipped", "skippedMessage", "status", "stderr", "stdout"
        }
    )
    public static TestCaseResult create(int age,
                                        String className,
                                        float duration,
                                        String errorDetails,
                                        String errorStackTrace,
                                        int failedSince,
                                        String testCaseName,
                                        boolean skipped,
                                        String skippedMessage,
                                        String status,
                                        String stderr,
                                        String stdout){
        return new AutoValue_TestCaseResult(
            age, className, duration, errorDetails, errorStackTrace, failedSince,
            testCaseName, skipped, skippedMessage, status, stderr, stdout
        );
    }

}
