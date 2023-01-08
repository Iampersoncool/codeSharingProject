package com.Hao.codeSharingProject.Config;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.Hao.codeSharingProject.CodeItem;

public interface CodeRepository<T, ID> extends MongoRepository<CodeItem, String> {
	@Query("{'uuid' : ?0}")
	CodeItem findByUUID(UUID uuid);
}
