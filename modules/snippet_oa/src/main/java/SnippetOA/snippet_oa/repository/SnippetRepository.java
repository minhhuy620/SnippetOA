package SnippetOA.snippet_oa.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import SnippetOA.snippet_oa.entity.Snippet;

@Transactional
@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Integer> {

}
