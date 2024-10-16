package com.monitor.service.gitee;

import com.monitor.domain.gitee.PullRequest;

public interface IGiteeService {
    /**
     * get files from pull request.
     * @param pr pull request.
     * @return files.
     */
    String getFilesFromPr(PullRequest pr);

    /**
     * get raw file.
     * @param owner owner.
     * @param repo repo.
     * @param path path.
     * @param ref ref.
     * @return raw file.s
     */
    String getRawFile(String owner, String repo, String path, String ref);
}
