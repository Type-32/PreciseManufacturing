import customtkinter


class HelperTabView(customtkinter.CTkTabview):
    def __init__(self, master, **kwargs):
        super().__init__(master, **kwargs)

        self.add("Cartridges")
        self.add("Guns")

        self.label = customtkinter.CTkLabel(self, text="Cartridges")


class HelperApp(customtkinter.CTk):
    def __init__(self):
        super().__init__()

        customtkinter.set_appearance_mode("dark")
        customtkinter.set_default_color_theme("dark-blue")

        self.title("Helper Application")
        self.geometry("800x500")
        self.set_widgets()

    def set_widgets(self):
        self.tab_view = HelperTabView(master=self)
        self.tab_view.pack(fill="both", expand=True, padx=15, pady=15)
        self.checkbox


root = HelperApp()
root.mainloop()
