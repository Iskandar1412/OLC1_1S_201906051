import React, { useState } from "react";

interface Tab {
  id: number;
  value1: string;
  value2: string;
}

export const IntroSection: React.FC = () => {
  const [tabs, setTabs] = useState<Tab[]>([]);
  const [activeTab, setActiveTab] = useState<number | null>(null);

  //--------------------------------------------
<<<<<<< HEAD
=======
  
>>>>>>> Otrosajustes
  //--------------------------------------------

  const handleTabClick = (id: number) => {
    setActiveTab(id);
  };

  const handleAddTab = () => {
    const newId = tabs.length === 0 ? 1 : tabs[tabs.length - 1].id + 1;
    const newTab: Tab = { id: newId, value1: "", value2: "" };
    setTabs([...tabs, newTab]);
    setActiveTab(newId);
  };

  const handleDeleteTab = (id: number) => {
    const newTabs = tabs.filter((tab) => tab.id !== id);
    setTabs(newTabs);
    setActiveTab(newTabs.length === 0 ? null : newTabs[0].id);
  };

  const handleCopyValue = (id: number) => {
    const newTabs = [...tabs];
    const tabIndex = newTabs.findIndex((tab) => tab.id === id);
    const value1 = newTabs[tabIndex].value1;
    newTabs[tabIndex] = { ...newTabs[tabIndex], value2: value1 };
    setTabs(newTabs);
  };

  //-------------------------------------
  const handleOpenFile = async () => {
    const fileInput = document.createElement("input");
    fileInput.type = "file";
    fileInput.accept = ".tw";
    fileInput.addEventListener("change", async (event) => {
      const target = event.target as HTMLInputElement;
      const file = target.files?.[0];
      if (file) {
        const fileText = await file.text();
        const newTabs = [...tabs];
        const tabIndex = newTabs.findIndex((tab) => tab.id === activeTab);
        newTabs[tabIndex] = { ...newTabs[tabIndex], value1: fileText };
        setTabs(newTabs);
      }
    });
    fileInput.click();
  };
<<<<<<< HEAD
=======
  //-------------------------------
  const handleSaveFile = async () => {
    const content = tabs.find((tab) => tab.id === activeTab)?.value1;
    if (!content) return;
    const blob = new Blob([content], { type: "text/plain" });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = "ArchivoProy.tw";
    link.click();
    URL.revokeObjectURL(url);
  };
>>>>>>> Otrosajustes
  //-------------------------------

  return (
    <>
      <h2 className="title-compiler">Analizador</h2>
      <div className="tabs-section">
        <div className="tabs-container">
          {tabs.map((tab) => (
            <div
              key={tab.id}
              className={`tab ${activeTab === tab.id ? "active" : ""}`}
              onClick={() => handleTabClick(tab.id)}
            >
              P{tab.id}
              <button
                className="delete-tab"
                onClick={() => handleDeleteTab(tab.id)}
              >
                𝒙
              </button>
            </div>
          ))}
          <button className="add-tab" onClick={handleAddTab}>
            +
          </button>
        </div>
        <div className="tab-content">
          {activeTab !== null && (
            <>
              <div className="content-text">
                <div className="editor">
                  <textarea
                    className="text-area"
                    value={tabs.find((tab) => tab.id === activeTab)?.value1}
                    onChange={(e) => {
                      const newTabs = [...tabs];
                      const tabIndex = newTabs.findIndex(
                        (tab) => tab.id === activeTab
                      );
                      newTabs[tabIndex] = {
                        ...newTabs[tabIndex],
                        value1: e.target.value,
                      };
                      setTabs(newTabs);
                    }}
                  ></textarea>
                </div>
                <div className="editor">
                  <textarea
                    className="text-area"
                    value={tabs.find((tab) => tab.id === activeTab)?.value2}
                    readOnly
                  ></textarea>
                </div>
              </div>
              <div className="content-button">
                <button
                  className="open-file"
                  onClick={() => {
                    handleOpenFile();
                  }}
                >
                  Open File
                </button>
                <button
                  className="acept-analyze"
                  onClick={() => handleCopyValue(activeTab)}
                >
                  Analyze
                </button>
                <button className="save-file" onClick={handleSaveFile}>
                  Save File
                </button>
              </div>
            </>
          )}
        </div>
      </div>
    </>
  );
};
