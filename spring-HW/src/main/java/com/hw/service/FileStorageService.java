package com.hw.service;

//@Service
public class FileStorageService {

	/*@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	private Path fileStorageLocation = null;
	
	 @Autowired
	    public FileStorageService(FileStorageProperties fileStorageProperties) {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }
	
	public UrlResource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            UrlResource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new ReportNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ReportNotFoundException("File not found " + fileName, ex);
        }
    }*/
}
