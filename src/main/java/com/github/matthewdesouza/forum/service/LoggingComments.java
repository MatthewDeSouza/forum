package com.github.matthewdesouza.forum.service;

import org.slf4j.Logger;

public interface LoggingComments {

    default void errorLogEntityNotFound(Logger log, Long id) {
        log.error("Entity (id '{}') not found within the database!", id);
    }

    default void errorLogEntityNotFound(Logger log, String name) {
        log.error("Entity (name '{}') not found within the database!", name);
    }

    default void errorLogEntityAlreadyExists(Logger log, Long id) {
        log.error("Entity (id '{}') already exists within the database!", id);
    }

    default void infoLogEntityRetrievePending(Logger log) {
        log.info("Retrieving all entities from the database.");
    }

    default void infoLogEntityRetrieveSuccess(Logger log) {
        log.info("Successfully retrieved all entities from the database!");
    }

    default void infoLogEntityRetrievePending(Logger log, Long id) {
        log.info("Retrieving entity (id '{}') from the database.", id);
    }

    default void infoLogEntityRetrieveSuccess(Logger log, Long id) {
        log.info("Entity (id '{}') successfully retrieved!", id);
    }

    default void infoLogEntityRetrievePending(Logger log, String name) {
        log.info("Retrieving entity (name '{}') from the database.", name);
    }

    default void infoLogEntityRetrieveSuccess(Logger log, String name) {
        log.info("Entity (name '{}') successfully retrieved!", name);
    }

    default void infoLogEntitySavePending(Logger log, Long id) {
        log.info("Saving entity (id '{}') to the database.", id);
    }

    default void infoLogEntitySaveSuccess(Logger log, Long id) {
        log.info("Entity (id '{}') successfully saved!", id);
    }

    default void infoLogEntityUpdatePending(Logger log, Long id) {
        log.info("Updating entity (id '{}') within the database.", id);
    }

    default void infoLogEntityUpdateSuccess(Logger log, Long id) {
        log.info("Entity (id '{}') successfully updated!", id);
    }

    default void infoLogEntityDeletePending(Logger log, Long id) {
        log.info("Deleting entity (id '{}') from the database.", id);
    }

    default void infoLogEntityDeletePending(Logger log, String name) {
        log.info("Deleting entity (name '{}') from the database.", name);
    }

    default void infoLogEntityDeleteSuccess(Logger log, Long id) {
        log.info("Entity (id '{}') successfully deleted!", id);
    }

    default void infoLogEntityDeleteSuccess(Logger log, String name) {
        log.info("Entity (name '{}') successfully deleted!", name);
    }
}
