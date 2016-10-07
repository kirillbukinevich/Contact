package logic.processcommand;

import logic.commands.*;
import logic.commands.addcommands.AddAttachmentCommand;
import logic.commands.addcommands.AddPhoneCommand;
import logic.commands.addcommands.AddPhotoCommand;
import logic.commands.deletecommands.DeleteAttachmentCommand;
import logic.commands.deletecommands.DeleteCommand;
import logic.commands.deletecommands.DeletePhoneCommand;
import logic.commands.deletecommands.DeletePhotoCommand;
import logic.commands.editcommands.*;
import logic.commands.emailcommand.template.ApplyTemplateEmailCommand;
import logic.commands.emailcommand.EmailCommand;
import logic.commands.emailcommand.SendMailCommand;
import logic.commands.maincommands.*;

public enum CommandEnum {
    NEW_PHONE {
        {
            this.command = new AddPhoneCommand();
        }
    },
    NEW_FILE {
        {
            this.command = new AddAttachmentCommand();
        }
    },
    APPLYTEMPLATEEMAILCOMMAND{
        {
            this.command = new ApplyTemplateEmailCommand();
        }
    },
    CONTACT {
        {
            this.command = new ContactCommand();
        }
    },CANCEL_EDIT{
        {
            this.command = new CancelEdit();
        }
    },
    DELETE {
        {
            this.command = new DeleteCommand();
        }
    },
    DELETEPHONE {
        {
            this.command = new DeletePhoneCommand();
        }
    },
    DELETEATTACHFILE {
        {
            this.command = new DeleteAttachmentCommand();
        }
    },
    DELETE_PHOTO{
        {
            this.command = new DeletePhotoCommand();
        }
    },
    EMAIL{
        {
            this.command = new EmailCommand();
        }
    },
    EDIT {
        {
            this.command = new EditCommand();
        }
    },
    EDIT_PHONE{
        {
            this.command = new EditPhoneCommand();
        }
    },
    EDIT_FILE{
        {
            this.command = new EditAttachmentCommand();
        }
    },
    EMPTY {
        {
            this.command = new EmptyCommand();
        }
    },
    NEW {
        {
            this.command = new NewCommand();
        }
    },
    SAVE {
        {
            this.command = new SaveCommand();
        }
    },
    SAVEPHOTO {
        {
            this.command = new AddPhotoCommand();
        }
    },
    SEARCH{
        {
            this.command = new SearchCommand();
        }
    },
    SENDEMAIL{
        {
            this.command = new SendMailCommand();
        }
    },
    UPDATE_ATTACHMENT{
        {
            this.command = new UpdateCommand();
        }
    },
    UPDATE_PHONE{
        {
            this.command = new UpdateCommand();
        }
    },
    UPDATE_EDIT_PHONE{
        {
            this.command = new ChooseEditPhoneCommand();
        }
    },
    UPDATE_EDIT_ATTACHMENT{
        {
            this.command = new ChooseEditAttachmentCommand();
        }
    },
    UPDATE_PHOTO{
        {
            this.command = new UpdateCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return this.command;
    }
}
