package logic.processcommand;

import logic.commands.*;
import logic.commands.addcommands.AddAttachmentCommand;
import logic.commands.addcommands.AddPhoneCommand;
import logic.commands.addcommands.AddPhotoCommand;
import logic.commands.deletecommands.DeleteAttachmentCommand;
import logic.commands.deletecommands.DeleteCommand;
import logic.commands.deletecommands.DeletePhoneCommand;
import logic.commands.editcommands.EditAttachmentCommand;
import logic.commands.editcommands.EditPhoneCommand;
import logic.commands.emailcommand.EmailCommand;
import logic.commands.emailcommand.SendMailCommand;
import logic.commands.maincommands.*;

public enum CommandEnum {
    ADDPHONE {
        {
            this.command = new AddPhoneCommand();
        }
    },
    ADDFILE {
        {
            this.command = new AddAttachmentCommand();
        }
    },
    CONTACT {
        {
            this.command = new ContactCommand();
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
            this.command = new EditPhoneCommand();
        }
    },
    UPDATE_EDIT_ATTACHMENT{
        {
            this.command = new EditAttachmentCommand();
        }
    };

    ActionCommand command;

    private CommandEnum() {
    }

    public ActionCommand getCurrentCommand() {
        return this.command;
    }
}
