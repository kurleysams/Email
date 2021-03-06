package com.barosanu.controller.services;

import javax.mail.Folder;

import com.barosanu.controller.ModelAccess;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FolderUpdaterService extends Service<Void>{
	
	private ModelAccess modelAccess;

	public FolderUpdaterService(ModelAccess modelAccess) {
		this.modelAccess = modelAccess;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>(){

			@Override
			protected Void call() throws Exception {
					for(;;){
						try {
							Thread.sleep(10000);
							if(modelAccess != null && FetchFoldersService.noServicesActive()){
								for(Folder folder: modelAccess.getFolderList()){
										if (folder.getType() != Folder.HOLDS_FOLDERS  && folder.isOpen()) {
											folder.getMessageCount();
										}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}			
		};
	}

}
