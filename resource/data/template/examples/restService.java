@Autowired
private ImageInfoGateway gateway;


@RequestMapping(value = "/metadata", method = {RequestMethod.POST})
public WebAsyncTask<Map>imageMetadataByImage(@RequestParam(value = "file", required = true)MultipartFile file)
{
    final MultipartFile _file=file;

    Callable<Map>callable=new Callable<Map>()
    {
        @Override
        public Map call()throws Exception
        {
            FileMetadataJob job=new FileMetadataJob();
            job.setDocumentId(null);
            job.setDocument(new Document(_file));
            job.getDocument().setContentType(_file.getContentType());
            job.getDocument().setFileName(_file.getOriginalFilename());

            Future<Map>imageFuture=imageMetadataGateway.getMetadata(job);
            FileMetadataJob payload=(FileMetadataJob)imageFuture.get(defaultTimeout,TimeUnit.MILLISECONDS);

            return payload.getMetadata();
        }
    };

    return new WebAsyncTask<Map>(defaultTimeout,callable);
}