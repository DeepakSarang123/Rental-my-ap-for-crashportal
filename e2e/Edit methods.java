public class Edit methods {
    

    @GetMapping(value={"/v1/crashReports")
    public List<CrashDumpModelDTO> getCrashDumpFileNames(@RequestParam(value="uUIDName",required=false) String uUIDName,
    @RequestParam(value="MacId",required=false) String MacId,@RequestParam(value="DateOfPost",required=false) String DateOfPost) {

        log.info("GetMapping(/crashReports)");
        List <CrashDumpModel>  crashDumpModelList = iCrashDumpUseCase.getAllCrashDumpInformation();
        List <CrashDumpModelDTO>  crashDumpModelDTOList = new ArrayList<>();
        
        if(!uUIDName.isEmpty()||!MacId.isEmpty()||!DateOfPost.isEmpty()){

            if(uUIDName.isEmpty()&&MacId.isEmpty()&&DateOfPost.isEmpty()){

                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    
                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID) && (MacId==MACID)
                    && (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);

                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((uUIDName==UUID) || (MacId==MACID) || (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);

                    /*    BeanUtils.copyProperties(crashDumpModelList, crashDumpModelDTOList);  You can try this instead of 
                           line 38 to 42 */
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!uUIDName.isEmpty()&&!MacId.isEmpty()) {
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    
                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID) && (MacId==MACID)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((uUIDName==UUID) || (MacId==MACID)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!MacId.isEmpty()&&!DateOfPost.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((MacId==MACID) && (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((MacId==MACID) || (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!uUIDName.isEmpty()&&!DateOfPost.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID) && (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }else if((uUIDName==UUID) || (DateOfPost==DATEOFPOST)){
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!uUIDName.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((uUIDName==UUID)
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!MacId.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((MacId==MACID)
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }else if (!DateOfPost.isEmpty()) {
                
                for(CrashDumpModel crashDumpModel:crashDumpModelList){

                    String UUID = crashDumpModel.getuUIDName();
                    String MACID = crashDumpModel.getMacId();
                    String DATEOFPOST = crashDumpModel.getDateOfPost();
                    String IMAGENAME = crashDumpModel.getImageName();
                    String VERSIONID = crashDumpModel.getVersionId();
                    CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();

                    if((DateOfPost==DATEOFPOST)
                        
                        crashDumpModelDTO.setDateOfPost(DATEOFPOST);
                        crashDumpModelDTO.setImageName(IMAGENAME);
                        crashDumpModelDTO.setMacId(MACID);
                        crashDumpModelDTO.setuUIDName(UUID);
                        crashDumpModelDTO.setVersionId(VERSIONID);
                        
                        crashDumpModelDTOList.add(crashDumpModelDTO);
                    }
                }return crashDumpModelDTOList;

            }
        }else{
            List <CrashDumpModelDTO>  crashDumpModelDTOList = new ArrayList<>();
            for(CrashDumpModel crashDumpModel:crashDumpModelList){
                CrashDumpModelDTO crashDumpModelDTO = new CrashDumpModelDTO();
                crashDumpModelDTO.setDateOfPost(crashDumpModel.getDateOfPost());
                crashDumpModelDTO.setImageName(crashDumpModel.getImageName());
                crashDumpModelDTO.setMacId(crashDumpModel.getMacId());
                crashDumpModelDTO.setuUIDName(crashDumpModel.getuUIDName());
                crashDumpModelDTO.setVersionId(crashDumpModel.getVersionId());
    
                crashDumpModelDTOList.add(crashDumpModelDTO);
            }
            return crashDumpModelDTOList;
        }





        
    }

}