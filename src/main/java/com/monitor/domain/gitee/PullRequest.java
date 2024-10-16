package com.monitor.domain.gitee;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PullRequest {
    /**
     * id.
     */
    private Integer id;

    /**
     * owner.
     */
    private String owner;

    /**
     * repo.
     */
    private String repo;

    /**
     * create PullRequest object.
     * @param id id.
     * @param owner owner.
     * @param repo repo.
     * @return PullRequest object.
     */
    public static PullRequest of(Integer id, String owner, String repo) throws IllegalArgumentException {
        if (Objects.isNull(id) || StringUtils.isBlank(owner) || StringUtils.isBlank(repo)) {
            throw new IllegalArgumentException("not enough, id: " + id + ", owner: " + owner + ", rpeo: " + repo);
        }

        PullRequest pr = new PullRequest();
        pr.setId(id);
        pr.setOwner(owner);
        pr.setRepo(repo);
        return pr;
    }
}
