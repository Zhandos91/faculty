package com.epam.suleimenov.service;

import com.epam.suleimenov.dao.ArchiveDAO;
import com.epam.suleimenov.dao.DAOFactory;
import com.epam.suleimenov.model.Archive;

import java.util.List;

public class ArchiveService {

    private DAOFactory daoFactory;
    private ArchiveDAO archiveDAO;

    public ArchiveService() {
        daoFactory = Service.getDAOFactory();
        archiveDAO = (ArchiveDAO) daoFactory.createDAO(Archive.class);
    }

        public boolean clearArchive() {
        return archiveDAO.clear();
        }

        public Archive createArchive(Archive archive) {
        return archiveDAO.create(archive);
        }

        public boolean deleteArchive(Object id) {
         return archiveDAO.delete(id);
        }

        public Archive findArchive(Object id) {
            return archiveDAO.find(id);
        }

        public List<Archive> getAllArchives() {
            return archiveDAO.getAll();
        }

        public Archive updateArchive(Archive archive) {
            return  archiveDAO.update(archive);
        }

}
